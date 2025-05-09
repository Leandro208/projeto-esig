package io.github.Leandro208.projetoESIG.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import io.github.Leandro208.projetoESIG.dao.GenericDao;
import io.github.Leandro208.projetoESIG.entities.Cargo;

@ManagedBean
@SessionScoped
public class CargoMBean extends AbstractMBean{

	private Cargo cargo;
	private GenericDao<Cargo> dao;
	
	public CargoMBean() {
		cargo = new Cargo();
		dao = new GenericDao<Cargo>();
	}
	
	public String salvar() {
		dao.salvar(cargo);
		cargo = new Cargo();
		return "form_cargo";
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
}
