package br.com.ufcg.domain;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.ufcg.dao.FornecedorDAO;
import br.com.ufcg.dao.UsuarioDAO;
import br.com.ufcg.domain.enums.TipoUsuario;

@Entity
@DiscriminatorValue(value = "Fornecedor")
@Embeddable
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Fornecedor extends Usuario {

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIO_HAS_ESPECIALIDADE", 
			   joinColumns = { @JoinColumn(name = "USUARIO_ID") },
			   inverseJoinColumns = { @JoinColumn(name = "ESPECIALIDADE_ID") })
	private List<Especialidade> listaEspecialidades;

	public Fornecedor(String nomeCompleto, String login, String fotoPerfil, String email, String senha,
			List<Especialidade> listaEspecialidades) {
		super(nomeCompleto, login, fotoPerfil, email, senha, TipoUsuario.FORNECEDOR);
		this.listaEspecialidades = listaEspecialidades;
	}

	public Fornecedor() {
		super(null, null, null, null, null, TipoUsuario.FORNECEDOR);
	}

	public List<Especialidade> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(List<Especialidade> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

	@Override
	public String toString() {
		return "Fornecedor " + super.getId() + ": " + "nome - " + super.getNomeCompleto() + " | Especialidade(s) - "
				+ listaEspecialidades.toString();
	}

	@Override
	public UsuarioDAO toDAO() {
		return new FornecedorDAO(this.getId(), this.getLogin(), this.getNomeCompleto(), this.getEmail(),
				this.getFotoPerfil(), this.getTipo(), this.getListaEspecialidades());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		if (listaEspecialidades == null) {
			if (other.listaEspecialidades != null)
				return false;
		} else if (!listaEspecialidades.equals(other.listaEspecialidades))
			return false;
		return true;
	}
}
