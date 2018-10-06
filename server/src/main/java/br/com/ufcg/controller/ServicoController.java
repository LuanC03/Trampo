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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @RequestMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Response> cadastrarServico(HttpServletRequest request, @RequestBody Servico servico) {

        Response response;

        try {
            Cliente cliente = (Cliente) request.getAttribute("user");
            servico.setCliente(cliente);
            servico.setStatus(TipoStatus.EM_ABERTO);

            Servico servicoCadastrado = servicoService.criarServico(servico);

            response = new Response("Serviço cadastrado com sucesso !", HttpStatus.OK.value(), servicoCadastrado);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
    
    @RequestMapping(value = "/api/servicos/fornecedor/{login}")
    public ResponseEntity<Response> getServicosDisponiveisForThisFornecedor(HttpServletRequest request, @PathVariable("login") String login) {
  
    	Response response;
    	
    	try {
    		Fornecedor fornecedor = (Fornecedor) request.getAttribute("user");
    		
    		if(fornecedor.getLogin().equals(login)) {
    			List<Servico> servicosDoFornecedor = servicoService.getServicosFornecedor(fornecedor);
    			
    			if(!servicosDoFornecedor.isEmpty()) {
    				List<Servico> servicosOrdenados = servicoService.ordenaServicosPorData(servicosDoFornecedor);
    				response = new Response("Servicos em aberto disponiveis para voce", HttpStatus.ACCEPTED.value(), servicosOrdenados);
        			return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    			}
    			
    			response = new Response("Nao encontramos servicos disponiveis para voce", HttpStatus.ACCEPTED.value());
    			return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    		}
    		
    		response = new Response("Acesso negado: login nao autenticado", HttpStatus.UNAUTHORIZED.value());
    		return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);
    		
    	} catch(Exception e) {
    		response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	}
    }
}
