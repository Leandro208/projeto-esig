package io.github.Leandro208.projetoESIG.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.dao.GenericDao;
import io.github.Leandro208.projetoESIG.entities.Cargo;
import io.github.Leandro208.projetoESIG.entities.Pessoa;
import io.github.Leandro208.projetoESIG.services.CargoService;

@ManagedBean
@ViewScoped
public class PessoaMBean extends AbstractMBean{

	private Pessoa pessoa;
	private GenericDao<Pessoa> dao;
	
	public PessoaMBean() {
		dao = new GenericDao<Pessoa>();
		pessoa = new Pessoa();
	}
	
	public String salvar() {
		dao.salvar(pessoa);
		pessoa = new Pessoa();
		return navegar("form_pessoa");
	}

	public List<SelectItem> getComboCargos() {
		CargoService cargoService = new CargoService();
		List<SelectItem> itensComboBoxCargos = new ArrayList<>();
		List<Cargo> cargos = cargoService.buscarTodos();
		for (Cargo c : cargos) {
			boolean isSelecionado = pessoa.getCargo() != null && pessoa.getCargo().getId() != null
					&& pessoa.getCargo().equals(c);
			itensComboBoxCargos.add(new SelectItem(c, c.getNome(), null, isSelecionado));

		}
		return itensComboBoxCargos;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
