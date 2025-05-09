package io.github.Leandro208.projetoESIG.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.entities.Vencimento;
import io.github.Leandro208.projetoESIG.enums.TipoVencimento;
import io.github.Leandro208.projetoESIG.services.VencimentoService;

@ManagedBean
@SessionScoped
public class VencimentoMBean extends AbstractMBean{

	private Vencimento vencimento;
	private final VencimentoService service;
	
	public VencimentoMBean() {
		service = new VencimentoService();
		vencimento = new Vencimento();
	}
	
	public String salvar() {
		service.salvar(vencimento);
		vencimento = new Vencimento();
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
