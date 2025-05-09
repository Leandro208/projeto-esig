package io.github.Leandro208.projetoESIG.bean;

public abstract class AbstractMBean {

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
