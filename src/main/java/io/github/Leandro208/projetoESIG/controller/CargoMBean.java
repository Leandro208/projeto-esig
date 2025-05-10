package io.github.Leandro208.projetoESIG.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import io.github.Leandro208.projetoESIG.dominio.Cargo;
import io.github.Leandro208.projetoESIG.negocio.ListaComando;
import io.github.Leandro208.projetoESIG.negocio.Movimento;
import io.github.Leandro208.projetoESIG.negocio.MovimentoCadastro;

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
		Movimento movimento = new MovimentoCadastro();
		movimento.setComando(ListaComando.CADASTRAR);
		movimento.setEntidade(cargo);
		try {
			execute(movimento);
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
