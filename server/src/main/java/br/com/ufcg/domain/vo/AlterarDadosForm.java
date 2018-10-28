package br.com.ufcg.domain.vo;
import java.util.List;

import javax.validation.constraints.Email;

import br.com.ufcg.domain.*;

public class AlterarDadosForm {
	
	private String novoNomeCompleto;
	private String novaSenha;
	@Email(message = "Formato de email invalido!")
	private String novoEmail;
	private String novoLogin;
	private List<Especialidade> novaEspecialidades;
	private String novaFotoPerfil;
	
	public AlterarDadosForm(String novoNomeCompleto, String novoLogin, String novoEmail, String novaSenha, List<Especialidade> novaEspecialidades, String fotoPerfil) {
		this.novoNomeCompleto = novoNomeCompleto;
		this.novoLogin = novoLogin;
		this.novoEmail = novoEmail;
		this.novaSenha = novaSenha;
		this.novaEspecialidades = novaEspecialidades;
		this.setNovaFotoPerfil(fotoPerfil);
	}
	
	public String getNovoNomeCompleto() {
		return novoNomeCompleto;
	}
	public void setNovoNomeCompleto(String novoNomeCompleto) {
		this.novoNomeCompleto = novoNomeCompleto;
	}
	public String getNovaSenha() {
		return novaSenha;
	}
	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	public String getNovoEmail() {
		return novoEmail;
	}
	public void setNovoEmail(String novoEmail) {
		this.novoEmail = novoEmail;
	}
	public String getNovoLogin() {
		return novoLogin;
	}
	public void setNovoLogin(String novoLogin) {
		this.novoLogin = novoLogin;
	}
	public List<Especialidade> getNovaEspecialidades() {
		return novaEspecialidades;
	}
	public void setNovaEspecialidades(List<Especialidade> novasEspecialidades) {
		this.novaEspecialidades = novasEspecialidades;
	}

	public String getNovaFotoPerfil() {
		return novaFotoPerfil;
	}

	public void setNovaFotoPerfil(String fotoPerfil) {
		this.novaFotoPerfil = fotoPerfil;
	}
	
}
