package br.com.ufcg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufcg.dao.UsuarioDAO;
import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.domain.Fornecedor;
import br.com.ufcg.domain.Usuario;
import br.com.ufcg.domain.enums.TipoUsuario;
import br.com.ufcg.domain.vo.NovaSenhaForm;
import br.com.ufcg.repository.UsuarioRepository;
import br.com.ufcg.util.validadores.UsuarioValidador;

@Service
public class UsuarioService {
	
	// CONSTANTES NECESSÁRIAS AO SERVICE
	

	private static final String USUARIO_NAO_ENCONTRADO_EXCEPTION = "Usuario nao encontrado";
	private static final String EMAIL_LOGIN_JA_EXISTENTE_EXCEPTION = "Email e/ou login já estão sendo usados. Tente outros, por favor.";
	private static final String FORNECEDOR_SEM_ESPECIALIDADE = "O fornecedor tem que ter ao menos 1 especialidade";
	

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	EspecialidadeService especialidadeService;
	

	
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
			if(usuario.getTipo().equals(TipoUsuario.FORNECEDOR)) {
				usuario = criarFornecedor(usuario, ((Fornecedor) usuario).getListaEspecialidades());
			}
			return usuarioRepository.save(usuario);
		} 
		
		throw new Exception("Problemas ao cadastrar usuario! Campos invalidos!");
	}
	
	private Usuario criarFornecedor(Usuario usuario, List<Especialidade> especialidades) throws Exception {
		List<Especialidade> especialidadesValidas = setEspecialidadesFornecedor(((Fornecedor) usuario).getListaEspecialidades());
		
		if(especialidadesValidas.size() > 0) {
			((Fornecedor) usuario).setListaEspecialidades(especialidadesValidas);
		
			return usuario;
		}
		
		throw new Exception(FORNECEDOR_SEM_ESPECIALIDADE);
	}
	
	private List<Especialidade> setEspecialidadesFornecedor(List<Especialidade> especialidades) {
		
		return especialidadeService.getEspecialidadesValidas(especialidades);
		
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

	
	public List<UsuarioDAO> getClientes() {
		Iterable<Usuario> listaUsuarios = usuarioRepository.findAll();
		ArrayList<UsuarioDAO> clientes = new ArrayList<UsuarioDAO>();
		
		for (Usuario usuario : listaUsuarios) {
			if (TipoUsuario.CLIENTE.equals(usuario.getTipo())) {
				clientes.add(usuario.toDAO());
			}
		}
		
		return clientes;
	}
	
	public List<UsuarioDAO> getFornecedores() {
		Iterable<Usuario> listaUsuarios = usuarioRepository.findAll();
		ArrayList<UsuarioDAO> fornecedores = new ArrayList<UsuarioDAO>();
		
		for (Usuario usuario : listaUsuarios) {
			if (TipoUsuario.FORNECEDOR.equals(usuario.getTipo())) {
				fornecedores.add(usuario.toDAO());
			}
		}
		
		return fornecedores;
	}

	public void atualizarSenha(Usuario usuario, NovaSenhaForm form) throws Exception {
		String senhaNova = form.getSenhaNova();
		UsuarioValidador.validaSenha(senhaNova);
		String senhaAntiga = form.getSenhaAtiga();
		
		if(senhaNova.equals(senhaAntiga)) {
			throw new Exception("A senha atual tem que ser diferente da nova!");
		}
		
		if(!usuario.getSenha().equals(senhaAntiga)) {
			throw new Exception("A senha atual informada esta incorreta!");
		}
		
		usuario.setSenha(senhaNova);
		usuarioRepository.saveAndFlush(usuario);
		
	}
	
	public void atualizarNome(Usuario usuario, String nomeCompleto) throws Exception {
		if(UsuarioValidador.validaNome(nomeCompleto.trim())) {
			usuario.setNomeCompleto(nomeCompleto.trim().toLowerCase());
			usuarioRepository.saveAndFlush(usuario);
		}
		
	}
	
	public void atualizarFotoDoPerfil(Usuario usuario, String fotoPerfil) {
		usuario.setFotoPerfil(fotoPerfil);
		usuarioRepository.saveAndFlush(usuario);
	}
}
