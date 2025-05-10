package io.github.Leandro208.projetoESIG.controller;

public abstract class AbstractMBean {

	protected final String BOTAO_CADASTRAR = "Cadastrar";

	protected final String BOTAO_ALTERAR = "Alterar";
	
	private String confirmButton = BOTAO_CADASTRAR;
	
	public String getConfirmButton() {
		return confirmButton;
	}

	public void setConfirmButton(String confirmButton) {
		this.confirmButton = confirmButton;
	}
	
	public String cancelar() {
		return navegar("index");
	}
	
	
	public String navegar(String caminho) {
	    if (caminho.endsWith(".jsf") || caminho.endsWith(".xhtml")) {
	        caminho = caminho.substring(0, caminho.lastIndexOf("."));
	    }
	    return caminho + "?faces-redirect=true";
	}
}
