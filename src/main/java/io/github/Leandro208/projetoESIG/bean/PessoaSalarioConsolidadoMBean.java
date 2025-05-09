package io.github.Leandro208.projetoESIG.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import io.github.Leandro208.projetoESIG.dao.GenericDao;
import io.github.Leandro208.projetoESIG.entities.Pessoa;
import io.github.Leandro208.projetoESIG.entities.PessoaSalarioConsolidado;
import io.github.Leandro208.projetoESIG.services.PessoaSalarioConsolidadoService;
import io.github.Leandro208.projetoESIG.services.PessoaService;

@ManagedBean
@SessionScoped
public class PessoaSalarioConsolidadoMBean extends AbstractMBean {

	private PessoaSalarioConsolidadoService service;
	
	private List<PessoaSalarioConsolidado> lista;
	
	public PessoaSalarioConsolidadoMBean() {
		service = new PessoaSalarioConsolidadoService();
		lista = service.buscarTodos() == null ? new ArrayList<PessoaSalarioConsolidado>() : service.buscarTodos();
	}
	
	public void calcular() {
		service.calcular();
		lista = service.buscarTodos();
	}
	
	public Collection<PessoaSalarioConsolidado> getLista(){
		return lista;
	}
	
}
