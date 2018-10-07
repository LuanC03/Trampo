package br.com.ufcg.util.validadores;

import br.com.ufcg.domain.Usuario;

public class UsuarioValidador {
	
	private static final int TAMANHO_MINIMO_NOME = 8;
	private static final int TAMANHO_MAXIMO_NOME = 50;
	private static final int TAMANHO_MINIMO_SENHA = 8;
	private static final int TAMANHO_MAXIMO_SENHA = 20;
	private static final int TAMANHO_MINIMO_LOGIN = 4;
	private static final int TAMANHO_MAXIMO_LOGIN = 15;
	
	private static final String TAMANHO_MAXIMO_LOGIN_EXCEPTION = "O login deve ter no maximo 15 digitos";
	private static final String TAMANHO_MAXIMO_SENHA_EXCEPTION = "A senha deve ter no maximo 20 digitos";
	private static final String TAMANHO_MAXIMO_NOME_EXCEPTION = "O nome completo deve ter no maximo 50 digitos";
	private static final String TAMANHO_MINIMO_NOME_EXCEPTION = "O nome completo deve ter no minimo 8 digitos";
	private static final String TAMANHO_MINIMO_LOGIN_EXCEPTION = "O login deve ter no minimo 4 digitos e nao pode conter espaco";
	private static final String TAMANHO_MINIMO_SENHA_EXCEPTION = "A senha deve ter no minimo 8 digitos";
	
	public static boolean validaUsuario(Usuario usuario) throws Exception {
		boolean senhaComMaisDe8Digitos = validaSenha(usuario.getSenha());
		boolean loginValido = validaLogin(usuario.getLogin());
		boolean nomeValido = validaNome(usuario.getNomeCompleto());
		
		return (senhaComMaisDe8Digitos && loginValido && nomeValido);
	}
	
	private static boolean validaNome(String nomeCompleto) throws Exception {
		int tamanho = nomeCompleto.length();
		
		if (tamanho < TAMANHO_MINIMO_NOME) {
			throw new Exception(TAMANHO_MINIMO_NOME_EXCEPTION);
		}
		
		if(tamanho > TAMANHO_MAXIMO_NOME) {
			throw new Exception(TAMANHO_MAXIMO_NOME_EXCEPTION);
		}
		
		return true;
	}

	private static boolean validaLogin(String login) throws Exception  {
		int tamanho = login.length();
		boolean loginComEspaco = login.contains(" ");
		
		if ((tamanho < TAMANHO_MINIMO_LOGIN) && !loginComEspaco) {
			throw new Exception(TAMANHO_MINIMO_LOGIN_EXCEPTION);
		}
		
		if(tamanho > TAMANHO_MAXIMO_LOGIN) {
			throw new Exception(TAMANHO_MAXIMO_LOGIN_EXCEPTION);
		}
		
		return true;
	}
	
	private static boolean validaSenha(String senha) throws Exception {
		int tamanho = senha.length();
		
		if (tamanho < TAMANHO_MINIMO_SENHA) {
			throw new Exception(TAMANHO_MINIMO_SENHA_EXCEPTION);
		}
		
		if(tamanho > TAMANHO_MAXIMO_SENHA) {
			throw new Exception(TAMANHO_MAXIMO_SENHA_EXCEPTION);
		}
		
		return true;
	}
}
