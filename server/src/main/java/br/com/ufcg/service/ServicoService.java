package br.com.ufcg.service;

import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.domain.Fornecedor;
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
        servico.setTipo(servico.getTipo().toLowerCase());
        Servico hasServico = servicoRepository.findServico(servico.getData(), servico.getHorario(), servico.getCliente(), servico.getTipo());
		
		if (hasServico != null) {
            throw new Exception("Serviço já cadastrado no banco de dados.");
        }
		 
		return servicoRepository.save(servico);
    }
   
	public List<Servico> getServicosFornecedor(Fornecedor fornecedor){
    	
    	List<Servico> servicosDisponiveisFornecedor = new ArrayList<>();
    	List<Servico> servicos = this.getAll();
    	List<Especialidade> especialidadeFornecedor = fornecedor.getListaEspecialidades();
    	
    	for(Servico servico : servicos ) {
    		for(Especialidade especialidade : especialidadeFornecedor) {
    			//Checando se o fornecedor tem especialidade para realizar tal servico e se o servico em questao esta em aberto
    			if(servico.getTipo().equalsIgnoreCase(especialidade.getNome()) && servico.getStatus().equals(TipoStatus.EM_ABERTO)) {
    				servicosDisponiveisFornecedor.add(servico);
    			}
    		}
    	}
    	
    	return servicosDisponiveisFornecedor;
    	
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
	
	public Servico setServicoParaFornecedor(Servico servico, Fornecedor fornecedor) {
		Servico servicoAtualizado = servico;
		servicoAtualizado.setStatus(TipoStatus.ACEITO);
		servicoAtualizado.setFornecedor(fornecedor);
		servicoRepository.save(servicoAtualizado);
		
		return servicoAtualizado;
	}
	
	public Servico getServico(Servico servico) {
		Servico foundServico = servicoRepository.findServico(servico.getData(), servico.getHorario(), servico.getTipo().toLowerCase());
		return foundServico;
	}

	public boolean servicoEhValidoParaFornecedor(Servico servico, Fornecedor fornecedor) {
		boolean ehValido = false;
		List<Especialidade> especialidadesDoFornecedor = fornecedor.getListaEspecialidades();
		Servico foundServico = getServico(servico);
		for(Especialidade esp: especialidadesDoFornecedor) {
			if(esp.getNome().equalsIgnoreCase(foundServico.getTipo()) && foundServico.getStatus().equals(TipoStatus.EM_ABERTO)) {
				ehValido = true;
			}
		}
		
		return ehValido;
	}

}
