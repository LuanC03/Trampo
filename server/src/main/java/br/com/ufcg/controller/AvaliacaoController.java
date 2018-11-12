package br.com.ufcg.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufcg.domain.Usuario;
import br.com.ufcg.dto.AvaliacaoDTO;
import br.com.ufcg.service.AvaliacaoService;
import br.com.ufcg.service.ServicoService;
import br.com.ufcg.service.UsuarioService;
import br.com.ufcg.util.response.Response;

@RestController
public class AvaliacaoController {

	@Autowired
	AvaliacaoService avaliacaoService;
	
	@Autowired
	ServicoService servicoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	 @PostMapping(value = "/api/usuarios/avaliacao/avaliar")
	    public @ResponseBody ResponseEntity<Response> avaliarUsuario(HttpServletRequest request, @RequestBody AvaliacaoDTO avaliacao) {
	    	Response response;
	    	
	    	try {
	    		Usuario avaliador = (Usuario) request.getAttribute("user");
	    		avaliacaoService.avaliarUsuario(avaliador, avaliacao);
	    		response = new Response("O usuário foi avaliado com sucesso!", HttpStatus.OK.value(), avaliacao.getAvaliacao());
	    		return new ResponseEntity<>(response, HttpStatus.OK);
	    	} catch(Exception e) {
	    		response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
	    		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    	}
	    }
	    
	    
	    @PostMapping(value = "/api/usuarios/avaliacao/get", produces = "application/json")
	    public @ResponseBody ResponseEntity<Response> getAvaliacaoMedia(@RequestBody String login) {
	    	Response response;
	    	
	    	try {
	    		Usuario user = usuarioService.getByLogin(login.toLowerCase());
	    		Double mediaAvaliacoes = avaliacaoService.calcularAvaliacaoMedia(user);
	    		response = new Response("Média das avaliações calculada com sucesso!", HttpStatus.OK.value(), mediaAvaliacoes);
	    		return new ResponseEntity<>(response, HttpStatus.OK);
	    	} catch(Exception e) {
	    		response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
	    		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    	}
	    }
	    
	    
}
