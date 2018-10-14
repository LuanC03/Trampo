package br.com.ufcg.controller;

import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Fornecedor;
import br.com.ufcg.domain.Servico;
import br.com.ufcg.domain.enums.TipoStatus;
import br.com.ufcg.service.ServicoService;
import br.com.ufcg.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ServicoController {

    @Autowired
    private ServicoService servicoService;
    
    @RequestMapping(value = "/api/servicos/fornecedor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Response> setServicoParaFornecedor(HttpServletRequest request, @RequestBody Servico servico) {
    	Response response;
    	
    	try {
    		Fornecedor fornecedor = (Fornecedor) request.getAttribute("user");
    		
    		if(servicoService.servicoEhValidoParaFornecedor(servico, fornecedor)) {
    			Servico servicoAtualizado = servicoService.getServicoByID(servico.getId());
    			servicoAtualizado = servicoService.setServicoParaFornecedor(servico, fornecedor);
    			
    			response = new Response("Servico designado para voce com sucesso!", HttpStatus.OK.value(), servicoAtualizado);
    			return new ResponseEntity<>(response, HttpStatus.OK);
    			
    		} else {
    			response = new Response("Esse servico nao esta disponivel para voce.", HttpStatus.BAD_REQUEST.value(), servico);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    		}
    		
    	} catch(Exception e) {
    		response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(value = "/api/servicos/fornecedor/aceitos", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Response> getServicoFornecedor(HttpServletRequest request) {
    	Response response;
    	
    	try {
    		Fornecedor fornecedor = (Fornecedor) request.getAttribute("user");
    		List<Servico> servicosAceitos = servicoService.getServicosDoFornecedor(fornecedor);
    		response = new Response("Servicos que o fornecedor aceitou!", HttpStatus.OK.value(), servicosAceitos);
    		return new ResponseEntity<>(response, HttpStatus.OK);
    	} catch(Exception e) {
    		response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    		
    	}
    }
    
    @RequestMapping(value = "/api/servicosCadastrados/", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Response> getServicos() {
    	Response response; 
    	
    	try {
    		List<Servico> servicosCadastrados = servicoService.getAll();
    		List<Servico> servicosOrdenados = servicoService.ordenaServicosPorData(servicosCadastrados);
    		
    		response = new Response("Servicos cadastrados no sistema", HttpStatus.OK.value(), servicosOrdenados);
    		return new ResponseEntity<>(response, HttpStatus.OK);
    	} catch(Exception e) {
    		response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
    		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	}
    }

    @RequestMapping(value = "/api/servicos/cliente", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Response> getServicosFromCliente(HttpServletRequest request) {
  
    	Response response;
    	
    	try {
    		Cliente cliente = (Cliente) request.getAttribute("user");
    		List<Servico> servicosDoCliente = servicoService.getServicosCliente(cliente);
    		List<Servico> servicosOrdenados = servicoService.ordenaServicosPorData(servicosDoCliente);
    		
        	response = new Response("Servicos em aberto do cliente", HttpStatus.ACCEPTED.value(), servicosOrdenados);
      
        	return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        	
    	} catch(Exception e) {
    		response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	}
    }
    

    
    @RequestMapping(value = "/api/servicos/cliente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Response> cadastrarServico(HttpServletRequest request, @RequestBody Servico servico) {

        Response response;

        try {
            Cliente cliente = (Cliente) request.getAttribute("user");
            servico.setCliente(cliente);
            servico.setStatus(TipoStatus.EM_ABERTO);

            Servico servicoCadastrado = servicoService.criarServico(servico);

            response = new Response("Serviço cadastrado com sucesso !", HttpStatus.OK.value(), servicoCadastrado);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
            
        } catch(Exception e) {
            response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
    
    @RequestMapping(value = "/api/servicos/fornecedor", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Response> getServicosDisponiveisForThisFornecedor(HttpServletRequest request) {
  
    	Response response;
    	
    	try {
    		Fornecedor fornecedor = (Fornecedor) request.getAttribute("user");
    		List<Servico> servicosDoFornecedor = servicoService.getServicosFornecedor(fornecedor);
    			
    		if(!servicosDoFornecedor.isEmpty()) {
    			List<Servico> servicosOrdenados = servicoService.ordenaServicosPorData(servicosDoFornecedor);
    			response = new Response("Servicos em aberto disponiveis para voce", HttpStatus.ACCEPTED.value(), servicosOrdenados);
        		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    		}
    			
    		response = new Response("Nao encontramos servicos disponiveis para voce", HttpStatus.ACCEPTED.value());
    		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    		
    	
    	} catch(Exception e) {
    		response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	}
    }
}
