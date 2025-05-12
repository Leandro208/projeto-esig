package io.github.Leandro208.projetoESIG.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.dominio.CargoVencimento;
import io.github.Leandro208.projetoESIG.negocio.ListaComando;
import io.github.Leandro208.projetoESIG.negocio.Movimento;
import io.github.Leandro208.projetoESIG.negocio.MovimentoCadastro;
import io.github.Leandro208.projetoESIG.service.CargoService;
import io.github.Leandro208.projetoESIG.service.VencimentoService;

@ManagedBean
@SessionScoped
public class CargoVencimentoMBean extends AbstractMBean {

	private CargoVencimento cargoVencimento;
	private final String FORM_CARGO_VENCIMENTO = "/restricted/form_cargo_vencimento";
	public CargoVencimentoMBean() {
		reset();
	}
	private void reset() {
		cargoVencimento = new CargoVencimento();
	}
	
	public String entrarCadastro() {
		reset();
		return navegar(FORM_CARGO_VENCIMENTO);
	}
	
	public String salvar() {
		Movimento movimento = new MovimentoCadastro();
		movimento.setComando(ListaComando.CADASTRAR);
		movimento.setEntidade(cargoVencimento);
		try {
			execute(movimento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addMensagem("Operação realizada com sucesso!");
		reset();
		return navegar(FORM_CARGO_VENCIMENTO);
	}
	
	public List<SelectItem> getComboCargos() {
		CargoService cargoService = new CargoService();
		return cargoService.getComboCargos(cargoVencimento.getCargo());
	}
	
	public List<SelectItem> getComboVencimentos() {
		VencimentoService vencimentoService = new VencimentoService();
		return vencimentoService.getComboVencimentos(cargoVencimento.getVencimento());
	}

	public CargoVencimento getCargoVencimento() {
		return cargoVencimento;
	}

	public void setCargoVencimento(CargoVencimento cargoVencimento) {
		this.cargoVencimento = cargoVencimento;
	}
	
}
