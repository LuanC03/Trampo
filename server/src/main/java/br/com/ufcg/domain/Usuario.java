package br.com.ufcg.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import br.com.ufcg.domain.enums.TipoUsuario;

@Entity
@Table(name = "TAB_USUARIO", uniqueConstraints = @UniqueConstraint(columnNames = "TX_LOGIN", name = "login"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "senha"})
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
	
	@Email(message = "Insira um email valido.")
	@Column(name = "TX_EMAIL", nullable = false)
	private String email;

	@Column(name = "TX_SENHA", nullable = false)
	@JsonIgnore
	private String senha;

	@Column(name = "CD_TIPO", nullable = false, updatable = false)
	@Enumerated
	private TipoUsuario tipo;

	public Usuario(String nomeCompleto, String login, String fotoPerfil, 
			String email, String senha, TipoUsuario tipo) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.login = login;
		this.fotoPerfil = fotoPerfil;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
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
	
	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}
}
