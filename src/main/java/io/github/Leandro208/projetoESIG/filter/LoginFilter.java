package io.github.Leandro208.projetoESIG.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.github.Leandro208.projetoESIG.dominio.Pessoa;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession();
		
		String url = httpRequest.getRequestURL().toString();
		Pessoa pessoa = (Pessoa) session.getAttribute("usuario");
		
		if(url.contains("/restricted") && (pessoa == null || pessoa.getId() ==null)) {
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath()+"/login.jsf");
		}else {
			chain.doFilter(request, response);
		}
	}
	
	@Override
	public void destroy() {

	}

}
