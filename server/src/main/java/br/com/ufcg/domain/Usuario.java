package br.com.ufcg.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.ufcg.dao.UsuarioDAO;
import br.com.ufcg.domain.enums.TipoUsuario;

@Entity
@Table(name = "TAB_USUARIO", uniqueConstraints = @UniqueConstraint(columnNames = "TX_LOGIN", name = "login"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class Usuario {

	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fotoPerfil == null) {
			if (other.fotoPerfil != null)
				return false;
		} else if (!fotoPerfil.equals(other.fotoPerfil))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nomeCompleto == null) {
			if (other.nomeCompleto != null)
				return false;
		} else if (!nomeCompleto.equals(other.nomeCompleto))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "ID_USUARIO")
	private Long id;

	@Column(name = "TX_NOME_COMPLETO")
	private String nomeCompleto;

	@Column(name = "TX_LOGIN")
	private String login;

	@Column(name = "CD_FOTO_PERFIL", nullable = false)
	private String fotoPerfil;
	
	@Column(name = "TX_EMAIL", nullable = false)
	private String email;

	@Column(name = "TX_SENHA", nullable = false)
	private String senha;

	@Column(name = "CD_TIPO", nullable = false, updatable = false)
	@Enumerated
	private TipoUsuario tipo;
	
    //@NamedQuery(name = "obterUsuarioPorUsuarioSenha", query = "select a from USUARIO_AVALIACOES a where a.usuario_id =: id_u")
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "USUARIO_AVALIACAO",
	joinColumns = { @JoinColumn(name="USUARIO_ID")  },
	inverseJoinColumns = { @JoinColumn(name="AVALIACAO_ID") })
	private List<Avaliacao> avaliacoes;

	
	public Usuario(String nomeCompleto, String login, String fotoPerfil, 
			String email, String senha, TipoUsuario tipo) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.login = login;
		this.fotoPerfil = fotoPerfil;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
		this.avaliacoes = new ArrayList<>();
	}

	
 	public Usuario() {
		super();
	}
 	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public List<Avaliacao> getAvaliacoes() {
		return this.avaliacoes;
	}
	
	public void addAvaliacao(Avaliacao avaliacao) {
		this.avaliacoes.add(avaliacao);
	}
	
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}
	
	public abstract UsuarioDAO toDAO();
}
