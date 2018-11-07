package br.com.ufcg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufcg.domain.Avaliacao;
import br.com.ufcg.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
	
	@Autowired
	AvaliacaoRepository avaliacaoRepository;
	
	public Avaliacao addAvaliacao(Avaliacao avaliacao) throws Exception{
		if(avaliacao.getNota() >= 0 && avaliacao.getNota() <= 5) {
			return avaliacaoRepository.save(avaliacao);
		}
		
		throw new Exception("A nota tem que ser entre 0 e 5!");
	}
	
	public Avaliacao getAvaliacao(Long id) {
		return avaliacaoRepository.find(id);
	}
	
	public List<Avaliacao> findAll() {
		return avaliacaoRepository.findAll();
	}
}
