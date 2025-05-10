package io.github.Leandro208.projetoESIG.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.dao.GenericDao;
import io.github.Leandro208.projetoESIG.dominio.Cargo;
import io.github.Leandro208.projetoESIG.dominio.Pessoa;
import io.github.Leandro208.projetoESIG.service.CargoService;
import io.github.Leandro208.projetoESIG.service.PessoaService;

@ManagedBean
@SessionScoped
public class PessoaMBean extends AbstractMBean{

	private final String FORM_PESSOA = "form_pessoa";
	private final String CONSULTA_PESSOA = "listar_pessoas";
	private final String VER_DETALHES = "view_pessoa";
	
	private Pessoa pessoa;
	private PessoaService service;
	
	private List<Pessoa> resultados;
	
	public PessoaMBean() {
		service = new PessoaService();
		reset();
	}
	
	private void reset() {
		resultados = new ArrayList<Pessoa>();
		pessoa = new Pessoa();
	}

	public String entrarCadastro() {
		reset();
		setConfirmButton(BOTAO_CADASTRAR);
		return navegar(FORM_PESSOA);
	}
	
	public String salvar() {
		service.salvar(pessoa);
		pessoa = new Pessoa();
		return navegar(FORM_PESSOA);
	}
	
	public String entrarListagemPessoas() {
		carregarResultados();
		return navegar(CONSULTA_PESSOA);
	}
	
	public String verDetalhes() {
		return navegar(VER_DETALHES);
	}
	
	public String entrarEdicao() {
		setConfirmButton(BOTAO_ALTERAR);
		return navegar(FORM_PESSOA);
	}
	
	public String remover() {
		service.remover(pessoa);
		return entrarListagemPessoas();
	}
	
	private void carregarResultados() {
		resultados = service.buscarTodos();
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

	public List<Pessoa> getResultados() {
		return resultados;
	}
	
	
	
}
