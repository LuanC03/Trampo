package br.com.ufcg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufcg.domain.Usuario;
import br.com.ufcg.domain.enums.TipoUsuario;
import br.com.ufcg.repository.UsuarioRepository;
import br.com.ufcg.util.validadores.UsuarioValidador;

@Service
public class UsuarioService {
	
	// CONSTANTES NECESSÁRIAS AO SERVICE
	

	private static final String USUARIO_NAO_ENCONTRADO_EXCEPTION = "Usuario nao encontrado";
	private static final String EMAIL_LOGIN_JA_EXISTENTE_EXCEPTION = "Email e/ou login já estão sendo usados. Tente outros, por favor.";
	

	@Autowired
	UsuarioRepository usuarioRepository;
	

	
	public Usuario getByLogin(String login) throws Exception {
		Usuario usuario = usuarioRepository.findByLogin(login);
		
		if (usuario == null) {
			throw new Exception(USUARIO_NAO_ENCONTRADO_EXCEPTION);
		}
		
		return usuario;
	}

	public boolean checkUser(String login, String senha) {
		Usuario userToCheck = usuarioRepository.findByLoginAndSenha(login, senha);

		
		return (userToCheck != null);
	}
	
	public Usuario criarUsuario(Usuario usuario) throws Exception {
		if (UsuarioValidador.validaUsuario(usuario) && usuarioEhUnico(usuario)) {
			return usuarioRepository.save(usuario);
		} 
		
		return null;
	}
	
	
	
	private boolean usuarioEhUnico(Usuario usuario) throws Exception {
		
		boolean ehUnico = true;
		String email = usuario.getEmail();
		String login = usuario.getLogin();
		
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		for(Usuario user:usuarios) {
			if(user.getLogin().equalsIgnoreCase(login) || user.getEmail().equalsIgnoreCase(email)) {
				ehUnico = false;
			}
		}
		
		
		if (!ehUnico) {
			throw new Exception(EMAIL_LOGIN_JA_EXISTENTE_EXCEPTION);
		}
		
		return true;
	}

	
	public List<Usuario> getClientes() {
		Iterable<Usuario> listaUsuarios = usuarioRepository.findAll();
		ArrayList<Usuario> clientes = new ArrayList<Usuario>();
		
		for (Usuario usuario : listaUsuarios) {
			if (TipoUsuario.CLIENTE.equals(usuario.getTipo())) {
				clientes.add(usuario);
			}
		}
		
		return clientes;
	}
	
	public List<Usuario> getFornecedores() {
		Iterable<Usuario> listaUsuarios = usuarioRepository.findAll();
		ArrayList<Usuario> fornecedores = new ArrayList<Usuario>();
		
		for (Usuario usuario : listaUsuarios) {
			if (TipoUsuario.FORNECEDOR.equals(usuario.getTipo())) {
				fornecedores.add(usuario);
			}
		}
		
		return fornecedores;
	}
}
