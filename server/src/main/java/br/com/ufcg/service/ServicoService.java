package br.com.ufcg.service;

import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Servico;
import br.com.ufcg.domain.enums.TipoStatus;
import br.com.ufcg.repository.ServicoRepository;
import br.com.ufcg.util.validadores.ServicoValidador;

import java.util.ArrayList;
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
		Iterable<Servico> it = servicoRepository.findAll();
		ArrayList<Servico> servicos = new ArrayList<>();
		
		for(Servico serv: it) {
			servicos.add(serv);
		}
		
		return servicos;
	}

	public List<Servico> getServicosCliente(Cliente cliente, TipoStatus status) {
		return servicoRepository.findServicosCliente(cliente, status);
	}
	
	public List<Servico> ordenaServicosPorData(List<Servico> servicos) {
		List<Servico> servicosOrdenados = servicos;
		Collections.sort(servicosOrdenados, new Comparator<Servico>(){

			  public int compare(Servico o1, Servico o2)
			  {
				  if(o1.getData().isAfter(o2.getData())) {
						return 1;
					} else if(o1.getData().isBefore(o2.getData())) {
						return -1;
					}
					
					return 0;
			  }
			});
		
		return servicosOrdenados;
	}
}
