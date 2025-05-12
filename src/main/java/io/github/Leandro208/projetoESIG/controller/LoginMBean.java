package io.github.Leandro208.projetoESIG.controller;


import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import io.github.Leandro208.projetoESIG.dominio.Pessoa;
import io.github.Leandro208.projetoESIG.service.PessoaService;
import io.github.Leandro208.projetoESIG.util.Criptografar;

@ManagedBean
@SessionScoped
public class LoginMBean  extends AbstractMBean implements Serializable{
	
	private String login;
	private String senha;
	private Pessoa pessoa;
	
	private final String INICIO_PAGE = "/restricted/home";
	private final String LOGIN_PAGE = "/login";
	
	public String logar() {
		PessoaService service = new PessoaService();
		
		List<Pessoa> pessoas = service.buscarTodos();
		
		for(Pessoa p : pessoas) {
			if(p.getUsuario().equals(login) && p.getSenha().equals(Criptografar.encriptografar(senha))) {
				pessoa = p;
			}
		}
		
		
		if(pessoa != null) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute("usuario", pessoa);
			return navegar(INICIO_PAGE);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha Invalidos!",""));
		return null;
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		pessoa = null;
		return navegar(LOGIN_PAGE);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	

}
