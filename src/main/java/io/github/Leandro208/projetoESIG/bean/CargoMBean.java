package io.github.Leandro208.projetoESIG.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import io.github.Leandro208.projetoESIG.entities.Cargo;
import io.github.Leandro208.projetoESIG.negocio.ListaComando;
import io.github.Leandro208.projetoESIG.negocio.Operacao;
import io.github.Leandro208.projetoESIG.negocio.OperacaoCadastro;

@ManagedBean
@SessionScoped
public class CargoMBean extends AbstractMBean{

	private Cargo cargo;
	private final String FORM_CARGO = "form_cargo"; 
	public CargoMBean() {
		reset();
	}
	
	public String entrarCadastro() {
		reset();
		return navegar(FORM_CARGO);
	}
	
	public String salvar() {
		
		Operacao operacao = new OperacaoCadastro();
		operacao.setComando(ListaComando.CADASTRAR);
		operacao.setEntidade(cargo);
		try {
			realizarOperacao(operacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addMensagem("Operação realizada com sucesso!");
		reset();
		return navegar(FORM_CARGO);
	}
	
	private void reset() {
		cargo = new Cargo();
	}
	
	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
}
