package io.github.Leandro208.projetoESIG.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.dominio.Cargo;
import io.github.Leandro208.projetoESIG.dominio.CargoVencimento;
import io.github.Leandro208.projetoESIG.dominio.Vencimento;
import io.github.Leandro208.projetoESIG.service.CargoService;
import io.github.Leandro208.projetoESIG.service.CargoVencimentoService;
import io.github.Leandro208.projetoESIG.service.VencimentoService;

@ManagedBean
@SessionScoped
public class CargoVencimentoMBean extends AbstractMBean {

	private CargoVencimento cargoVencimento;
	private final String FORM_CARGO_VENCIMENTO = "form_cargo_vencimento";
	private CargoVencimentoService service;
	
	public CargoVencimentoMBean() {
		cargoVencimento = new CargoVencimento();
		service = new CargoVencimentoService();
	}
	private void reset() {
		cargoVencimento = new CargoVencimento();
	}
	
	public String entrarCadastro() {
		reset();
		return navegar(FORM_CARGO_VENCIMENTO);
	}
	
	public String salvar() {
		service.salvar(cargoVencimento);
		cargoVencimento = new CargoVencimento();
		return navegar(FORM_CARGO_VENCIMENTO);
	}
	
	public List<SelectItem> getComboCargos() {
		CargoService cargoService = new CargoService();
		List<SelectItem> itensComboBoxCargos = new ArrayList<>();
		List<Cargo> cargos = cargoService.buscarTodos();
		for (Cargo c : cargos) {
			itensComboBoxCargos.add(new SelectItem(c, c.getNome(), null));

		}
		return itensComboBoxCargos;
	}
	
	public List<SelectItem> getComboVencimentos() {
		VencimentoService vencimentoService = new VencimentoService();
		List<SelectItem> itensComboBoxVencimentos = new ArrayList<>();
		List<Vencimento> vencimentos = vencimentoService.buscarTodos();
		for (Vencimento v : vencimentos) {
			itensComboBoxVencimentos.add(new SelectItem(v, v.getDescricao(), null));

		}
		return itensComboBoxVencimentos;
	}

	public CargoVencimento getCargoVencimento() {
		return cargoVencimento;
	}

	public void setCargoVencimento(CargoVencimento cargoVencimento) {
		this.cargoVencimento = cargoVencimento;
	}
	
}
