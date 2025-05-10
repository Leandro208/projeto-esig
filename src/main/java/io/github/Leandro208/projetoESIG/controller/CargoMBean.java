package io.github.Leandro208.projetoESIG.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import io.github.Leandro208.projetoESIG.dao.GenericDao;
import io.github.Leandro208.projetoESIG.dominio.Cargo;
import io.github.Leandro208.projetoESIG.service.CargoService;

@ManagedBean
@SessionScoped
public class CargoMBean extends AbstractMBean{

	private Cargo cargo;
	private CargoService service;
	private final String FORM_CARGO = "form_cargo"; 
	
	public CargoMBean() {
		cargo = new Cargo();
		service = new CargoService();
	}
	
	public String entrarCadastro() {
		reset();
		return navegar(FORM_CARGO);
	}
	
	private void reset() {
		cargo = new Cargo();
	}
	
	public String salvar() {
		service.salvar(cargo);
		cargo = new Cargo();
		return FORM_CARGO;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
}
