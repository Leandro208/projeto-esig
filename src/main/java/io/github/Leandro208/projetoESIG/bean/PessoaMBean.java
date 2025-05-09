package io.github.Leandro208.projetoESIG.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.entities.Pessoa;
import io.github.Leandro208.projetoESIG.negocio.ListaComando;
import io.github.Leandro208.projetoESIG.negocio.Operacao;
import io.github.Leandro208.projetoESIG.negocio.OperacaoCadastro;
import io.github.Leandro208.projetoESIG.services.CargoService;
import io.github.Leandro208.projetoESIG.services.PessoaService;

@ManagedBean
@SessionScoped
public class PessoaMBean extends AbstractMBean {

	private final String FORM_PESSOA = "form_pessoa";
	private final String CONSULTA_PESSOA = "listar_pessoas";
	private final String VER_DETALHES = "view_pessoa";
	private Pessoa pessoa;
	
	private List<Pessoa> resultados;

	public PessoaMBean() {
		reset();
	}

	private void reset() {
		resultados = new ArrayList<Pessoa>();
		pessoa = new Pessoa();
	}

	public String entrarCadastro() {
		reset();
		return navegar(FORM_PESSOA);
	}

	public String salvar() {
		Operacao operacao = new OperacaoCadastro();
		if (getConfirmButton().equals(BOTAO_CADASTRAR)) {
			operacao.setComando(ListaComando.CADASTRO_PESSOA);
		} else if (getConfirmButton().equals(BOTAO_ALTERAR)) {
			operacao.setComando(ListaComando.ALTERAR_PESSOA);
		}
		operacao.setEntidade(pessoa);
		try {
			realizarOperacao(operacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addMensagem("Operação realizada com sucesso!");
		reset();
		return navegar(FORM_PESSOA);
	}

	public String entrarListagemPessoas() {
		carregarResultados();
		return navegar(CONSULTA_PESSOA);
	}

	public List<SelectItem> getComboCargos() {
		CargoService cargoService = new CargoService();
		return cargoService.getComboCargos(pessoa.getCargo());
	}
	
	public String verDetalhes() {
		return navegar(VER_DETALHES);
	}
	
	public String entrarEdicao() {
		setConfirmButton(BOTAO_ALTERAR);
		return navegar(FORM_PESSOA);
	}
	
	public String remover() {
		Operacao operacao = new OperacaoCadastro();
		operacao.setComando(ListaComando.REMOVER_PESSOA);
		operacao.setEntidade(pessoa);
		try {
			realizarOperacao(operacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entrarListagemPessoas();
	}

	private void carregarResultados() {
		PessoaService service = new PessoaService();
		resultados = service.buscarTodos();
	}
	public List<Pessoa> getResultados() {
		return resultados;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
