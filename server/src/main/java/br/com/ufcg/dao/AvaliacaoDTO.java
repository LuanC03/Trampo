package br.com.ufcg.dao;

import br.com.ufcg.domain.Avaliacao;
import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Usuario;

public class AvaliacaoDTO {
	
	private Cliente usuario;
	private Avaliacao avaliacao;
	
	public AvaliacaoDTO() {
		
	}
	
	public AvaliacaoDTO(Cliente usuario, Avaliacao avaliacao) {
		this.usuario = usuario;
		this.avaliacao = avaliacao;
	}

	public Cliente getUsuario() {
		return usuario;
	}

	public void setUsuario(Cliente usuario) {
		this.usuario = usuario;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

}
