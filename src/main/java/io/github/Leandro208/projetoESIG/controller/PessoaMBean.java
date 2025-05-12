package io.github.Leandro208.projetoESIG.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.dominio.Pessoa;
import io.github.Leandro208.projetoESIG.dto.FormBuscaDTO;
import io.github.Leandro208.projetoESIG.exception.NegocioException;
import io.github.Leandro208.projetoESIG.negocio.ListaComando;
import io.github.Leandro208.projetoESIG.negocio.Movimento;
import io.github.Leandro208.projetoESIG.negocio.MovimentoCadastro;
import io.github.Leandro208.projetoESIG.service.CargoService;
import io.github.Leandro208.projetoESIG.service.PessoaService;

@ManagedBean
@SessionScoped
public class PessoaMBean extends AbstractMBean {

	private final String FORM_PESSOA = "/form_pessoa";
	private final String CONSULTA_PESSOA = "/restricted/listar_pessoas";
	private final String VER_DETALHES = "/restricted/view_pessoa";
	private Pessoa pessoa;
	private String confirmacaoSenha;
	private List<Pessoa> resultados;
	
	private FormBuscaDTO formBusca;

	public PessoaMBean() {
		reset();
	}

	private void reset() {
		resultados = new ArrayList<Pessoa>();
		pessoa = new Pessoa();
		formBusca = new FormBuscaDTO();
	}

	public String entrarCadastro() {
		reset();
		setConfirmButton(BOTAO_CADASTRAR);
		return navegar(FORM_PESSOA);
	}

	public String salvar() {
		
		if (!pessoa.getSenha().equals(confirmacaoSenha)) {
		    addMensagemErro("As senhas não coincidem.");
		    return null;
		}
		
		Movimento movimento = new MovimentoCadastro();
		if (getConfirmButton().equals(BOTAO_CADASTRAR)) {
			 movimento.setComando(ListaComando.CADASTRO_PESSOA);
		} else if (getConfirmButton().equals(BOTAO_ALTERAR)) {
			movimento.setComando(ListaComando.ALTERAR_PESSOA);
		}
		movimento.setEntidade(pessoa);
		try {
			execute(movimento);
		} catch (NegocioException e) {
			addMensagemErro(e.getMessage());
			return null;
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
		Movimento movimento = new MovimentoCadastro();
		movimento.setComando(ListaComando.REMOVER_PESSOA);
		movimento.setEntidade(pessoa);
		try {
			execute(movimento);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entrarListagemPessoas();
	}

	private void carregarResultados() {
		PessoaService service = new PessoaService();
		resultados = service.consultar(formBusca);
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

	public FormBuscaDTO getFormBusca() {
		return formBusca;
	}

	public void setFormBusca(FormBuscaDTO formBusca) {
		this.formBusca = formBusca;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
}
