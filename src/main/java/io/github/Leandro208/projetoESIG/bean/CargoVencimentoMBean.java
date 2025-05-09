package io.github.Leandro208.projetoESIG.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.entities.CargoVencimento;
import io.github.Leandro208.projetoESIG.negocio.ListaComando;
import io.github.Leandro208.projetoESIG.negocio.Operacao;
import io.github.Leandro208.projetoESIG.negocio.OperacaoCadastro;
import io.github.Leandro208.projetoESIG.services.CargoService;
import io.github.Leandro208.projetoESIG.services.VencimentoService;

@ManagedBean
@SessionScoped
public class CargoVencimentoMBean extends AbstractMBean {

	private CargoVencimento cargoVencimento;
	private final String FORM_CARGO_VENCIMENTO = "form_cargo_vencimento";
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
		Operacao operacao = new OperacaoCadastro();
		operacao.setComando(ListaComando.CADASTRAR);
		operacao.setEntidade(cargoVencimento);
		try {
			realizarOperacao(operacao);
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
