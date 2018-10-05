package br.com.ufcg.controller;

import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Servico;
import br.com.ufcg.domain.enums.TipoStatus;
import br.com.ufcg.service.ServicoService;
import br.com.ufcg.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/api/servicos/{login}")
    public ResponseEntity<Response> getServicosFromCliente(HttpServletRequest request, @PathVariable("login") String login) {
  
    	Response response;
    	
    	try {
    		Cliente cliente = (Cliente) request.getAttribute("user");
    		List<Servico> servicosDoCliente = servicoService.getServicosCliente(cliente, TipoStatus.EM_ABERTO);
    		List<Servico> servicosOrdenados = servicoService.ordenaServicosPorData(servicosDoCliente);
        	response = new Response("Servicos em aberto do cliente", HttpStatus.ACCEPTED.value(), servicosOrdenados);
        	return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    	} catch(Exception e) {
    		response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    	}
    }
    
    @GetMapping(value = "/api/servicos", produces="application/json")
    public List<Servico> getServicos() {
    	return servicoService.getAll();
    }
    
    @RequestMapping(value = "/api/servicos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
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
}
