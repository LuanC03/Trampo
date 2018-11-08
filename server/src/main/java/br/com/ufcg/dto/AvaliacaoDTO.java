package br.com.ufcg.dto;

import br.com.ufcg.domain.Avaliacao;
import br.com.ufcg.domain.Servico;

public class AvaliacaoDTO {
	
	private Avaliacao avaliacao;
	private Servico servico;
	
	public AvaliacaoDTO() {
		
	}
	
	public AvaliacaoDTO(Avaliacao avaliacao, Servico servico) {
		this.setAvaliacao(avaliacao);
		this.setServico(servico);
	}


	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}
