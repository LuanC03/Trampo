package br.com.ufcg.domain.vo;

public class NovaSenhaForm {
	
	private String senhaAtiga;
	private String senhaNova;
	
	public NovaSenhaForm(String senhaAntiga, String senhaNova) {
		this.senhaAtiga = senhaAntiga;
		this.senhaNova = senhaNova;
	}

	public String getSenhaAtiga() {
		return senhaAtiga;
	}

	public void setSenhaAtiga(String senhaAtiga) {
		this.senhaAtiga = senhaAtiga;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}
	
	
}
