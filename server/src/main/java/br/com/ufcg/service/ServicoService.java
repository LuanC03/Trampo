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

    private static final String FORNECEDOR_SEM_SERVICOS = "O fornecedor nao possui nenhum servico aceito.";
    
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
					} else if(servico1.getHorario().isAfter(servico2.getHorario())) {
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
		return servicoRepository.saveAndFlush(servicoAtualizado);
		
	}
	
	public Servico getServico(Servico servico) {
		Servico foundServico = servicoRepository.findServico(servico.getData(), servico.getHorario(), servico.getCliente(), servico.getTipo().toLowerCase());
		return foundServico;
	}
	
	public Servico getServicoByID(long id) throws Exception {
		Servico servico = servicoRepository.findByID(id);
		if(servico == null) {
			throw new Exception("Serviço não cadastrado no banco de dados.");
		}
		
		return servico;
	}

	public boolean servicoEhValidoParaFornecedor(Servico servico, Fornecedor fornecedor) {
		boolean ehValido = false;
		List<Especialidade> especialidadesDoFornecedor = fornecedor.getListaEspecialidades();
		Servico foundServico = servicoRepository.findByID(servico.getId());
		
		for(Especialidade esp: especialidadesDoFornecedor) {
			if(esp.getNome().equalsIgnoreCase(foundServico.getTipo()) && foundServico.getStatus().equals(TipoStatus.EM_ABERTO)) {
				ehValido = true;
			}
		}
		
		return ehValido;
	}

	public List<Servico> getServicosDoFornecedor(Fornecedor fornecedor) throws Exception {
		List<Servico> servicos = servicoRepository.findServicosFornecedor(fornecedor);
		
		if(servicos.size() == 0) {
			throw new Exception(FORNECEDOR_SEM_SERVICOS);
		}
		
		return servicos;
	}

}
