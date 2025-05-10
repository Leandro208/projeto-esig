package io.github.Leandro208.projetoESIG.controller;

import javax.faces.context.FacesContext;

import io.github.Leandro208.projetoESIG.negocio.ProcessadorComando;
import io.github.Leandro208.projetoESIG.negocio.Movimento;
import io.github.Leandro208.projetoESIG.util.Message;

public abstract class AbstractMBean {

	protected final String BOTAO_CADASTRAR = "Cadastrar";

	protected final String BOTAO_ALTERAR = "Alterar";
	
	private String confirmButton = BOTAO_CADASTRAR;
	
	public void execute(Movimento movimento) throws Exception {
		Class<?> clazz = Class.forName(movimento.getComando().getClasse());
		ProcessadorComando processador = (ProcessadorComando) clazz.getDeclaredConstructor().newInstance();
		processador.execute(movimento);

	}

	public void addMensagem(String mensagem) {
		Message.info(mensagem);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public void addMensagemErro(String mensagem) {
		Message.erro(mensagem);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public void addMensagemWarning(String mensagem) {
		Message.warn(mensagem);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public void addMensagemErroPadrao() {
		addMensagemErro("Um erro ocorreu durante a execução desta operação. ");
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public String getConfirmButton() {
		return confirmButton;
	}

	public void setConfirmButton(String confirmButton) {
		this.confirmButton = confirmButton;
	}
	
	public <U> U getDAO(Class<U> daoClass) {
	    try {
	        return daoClass.getDeclaredConstructor().newInstance();
	    } catch (Exception e) {
	        throw new RuntimeException("Erro ao instanciar a classe: " + daoClass.getName(), e);
	    }
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
