package io.github.Leandro208.projetoESIG.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.dominio.TipoVencimento;
import io.github.Leandro208.projetoESIG.dominio.Vencimento;
import io.github.Leandro208.projetoESIG.service.VencimentoService;

@ManagedBean
@SessionScoped
public class VencimentoMBean extends AbstractMBean{

	private final String FORM_VENCIMENTO = "form_vencimento";

	private Vencimento vencimento;
	private final VencimentoService service;
	
	public VencimentoMBean() {
		service = new VencimentoService();
		vencimento = new Vencimento();
	}
	
	private void reset() {
		vencimento = new Vencimento();
	}

	public String entrarCadastro() {
		reset();
		return navegar(FORM_VENCIMENTO);
	}
	
	public String salvar() {
		service.salvar(vencimento);
		vencimento = new Vencimento();
		return navegar(FORM_VENCIMENTO);
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
