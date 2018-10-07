package br.com.ufcg.service;

import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Servico;
import br.com.ufcg.repository.ServicoRepository;
import br.com.ufcg.util.validadores.ServicoValidador;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {


    @Autowired
    ServicoRepository servicoRepository;

    public Servico criarServico(Servico servico) throws Exception {
        ServicoValidador.valida(servico);
        Servico hasServico = servicoRepository.findServico(servico.getData(), servico.getHorario(), servico.getCliente(), servico.getTipo());
        if(hasServico != null) {
            throw new Exception("Serviço já cadastrado no banco de dados.");
        }
     return servicoRepository.save(servico);
    }

	public List<Servico> getAll() {
		return servicoRepository.findAll();
	}

	public List<Servico> getServicosCliente(Cliente cliente) {
		return servicoRepository.findServicosCliente(cliente);
	}
	
	public List<Servico> ordenaServicosPorData(List<Servico> servicos) {
		List<Servico> servicosOrdenados = servicos;
		Collections.sort(servicosOrdenados, new Comparator<Servico>(){

			  public int compare(Servico servico1, Servico servico2)
			  {
				  if(servico1.getData().isAfter(servico2.getData())) {
						return 1;
					} else if(servico1.getData().isBefore(servico2.getData())) {
						return -1;
					}
				  
				  if(servico1.getHorario().isAfter(servico2.getHorario())) {
					  return 1;
				  } else if(servico1.getHorario().isBefore(servico2.getHorario())) {
					  return -1;
				  }
					return 0;
			  }
			});
		
		return servicosOrdenados;
	}
}
