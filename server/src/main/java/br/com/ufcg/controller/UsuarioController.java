package br.com.ufcg.controller;

import java.util.Date;
import java.util.List;

import br.com.ufcg.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Fornecedor;
import br.com.ufcg.domain.Usuario;
import br.com.ufcg.domain.vo.LoginForm;
import br.com.ufcg.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class UsuarioController {

	@Autowired
    private UsuarioService usuarioService;
	
	// CONSTANTES NECESSÁRIAS AO CONTROLLER
    public static final String STRING_VAZIA = "";
    public static final String STRING_ESPACAMENTO = " ";
    public static final String USUARIO_NAO_EXISTENTE = "Usuario nao existe";
    public static final String SENHA_INCORRETA = "Senha incorreta";
    public static final String SECRET = "ProjetoES";
    public static final String LOGIN_SUCESSO = "Login efetuado com sucesso !";
    public static final Integer HORAS = (3600 * 1000);
    public static final Integer HORAS_NO_DIA = 24;

    @RequestMapping(value = "/api/login", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Response> login(@RequestBody LoginForm usuario) {

        Response response = new Response();

        if (usuario.getLogin() == null || usuario.getSenha() == null) {
            response.setMessage(STRING_VAZIA);
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        Usuario usuAutenticado;

        try {
             usuAutenticado = usuarioService.getByLogin(usuario.getLogin());
        }catch(Exception e) {
            response.setMessage(USUARIO_NAO_EXISTENTE);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }


        if (!usuario.getSenha().equals(usuAutenticado.getSenha())) {
            response.setMessage(SENHA_INCORRETA);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        
        String token = Jwts.builder()
				            .setSubject(new StringBuilder(usuario.getLogin()).append(STRING_ESPACAMENTO).append(usuario.getSenha()).toString())
				            .signWith(SignatureAlgorithm.HS512, SECRET)
				            .setExpiration(new Date(System.currentTimeMillis() + HORAS_NO_DIA * HORAS))
				            .compact();

        response.setMessage(LOGIN_SUCESSO);
        response.setData(new LoginResponse(token));
        response.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@GetMapping(value = "/api/cliente", produces="application/json")
	public @ResponseBody ResponseEntity<Response> listaClientes(){
		Response response;
		List<Usuario> clientesCadastrados =  usuarioService.getClientes();
		response = new Response("Clientes cadastrados no sistema", HttpStatus.OK.value(), clientesCadastrados);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/fornecedor", produces="application/json")
	public @ResponseBody ResponseEntity<Response> listaFornecedores(){
		Response response;
		List<Usuario> fornecedoresCadastrados =  usuarioService.getFornecedores();
		response = new Response("Fornecedores cadastrados no sistema", HttpStatus.OK.value(), fornecedoresCadastrados);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/cliente", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Response> cadastrarCliente(@RequestBody Cliente cliente)  {
		
		Response response;
		try {
			
			Usuario retorno = usuarioService.criarUsuario(cliente);
			response = new Response("Usuario cadastrado com sucesso!", HttpStatus.OK.value(), retorno);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception e) {
			response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}	
	    
	}

	@RequestMapping(value = "/api/fornecedor", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Response> cadastrarFornecedor(@RequestBody Fornecedor fornecedor) throws Exception {

		Response response;
		try {
			
			Usuario retorno = usuarioService.criarUsuario(fornecedor);
			response = new Response("Usuario cadastrado com sucesso!", HttpStatus.OK.value(), retorno);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch(Exception e) {
			response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}	
	}
	
	private class LoginResponse {
        String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}
