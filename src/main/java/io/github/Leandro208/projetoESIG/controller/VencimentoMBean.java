package io.github.Leandro208.projetoESIG.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.dominio.TipoVencimento;
import io.github.Leandro208.projetoESIG.dominio.Vencimento;
import io.github.Leandro208.projetoESIG.negocio.ListaComando;
import io.github.Leandro208.projetoESIG.negocio.Operacao;
import io.github.Leandro208.projetoESIG.negocio.OperacaoCadastro;

@ManagedBean
@SessionScoped
public class VencimentoMBean extends AbstractMBean {

	private Vencimento vencimento;
	private final String FORM_VENCIMENTO = "form_vencimento";

	public VencimentoMBean() {
		reset();
	}

	private void reset() {
		vencimento = new Vencimento();
	}

	public String entrarCadastro() {
		reset();
		return navegar(FORM_VENCIMENTO);
	}

	public String salvar() {
		Operacao operacao = new OperacaoCadastro();
		operacao.setComando(ListaComando.CADASTRAR);
		operacao.setEntidade(vencimento);
		try {
			realizarOperacao(operacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addMensagem("Operação realizada com sucesso!");
		reset();
		return navegar("form_vencimento");
	}

	public List<SelectItem> getComboTipoVencimento() {
		List<SelectItem> niveisStatus = new ArrayList<>();
		for (TipoVencimento t : TipoVencimento.values()) {
			niveisStatus.add(new SelectItem(t, t.name()));
		}
		return niveisStatus;
	}

	public Vencimento getVencimento() {
		return vencimento;
	}

	public void setVencimento(Vencimento vencimento) {
		this.vencimento = vencimento;
	}

}
