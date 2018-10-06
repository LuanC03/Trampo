package br.com.ufcg.service;

import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.domain.Fornecedor;
import br.com.ufcg.domain.Servico;
import br.com.ufcg.domain.enums.TipoStatus;
import br.com.ufcg.repository.ServicoRepository;
import br.com.ufcg.util.validadores.ServicoValidador;

import java.util.ArrayList;
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
    
   
    
    
    public List<Servico> getServicosFornecedor(Fornecedor fornecedor){
    	
    	List<Servico> servicosDisponiveisFornecedor = new ArrayList<>();
    	List<Servico> servicos = this.getAll();
    	List<Especialidade> especialidadeFornecedor = fornecedor.getListaEspecialidades();
    	
    	for(Servico servico : servicos ) {
    		for(Especialidade especialidade : especialidadeFornecedor) {
    			//Checando se o fornecedor tem especialidade para realizar tal servico e se o servico em questao esta em aberto
    			if(servico.getTipo().equals(especialidade.getNome()) && servico.getStatus().equals(TipoStatus.EM_ABERTO)) {
    				servicosDisponiveisFornecedor.add(servico);
    			}
    		}
    	}
    	
    	return servicosDisponiveisFornecedor;
    	
    }
    
    
}
