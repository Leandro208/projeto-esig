package io.github.Leandro208.projetoESIG.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.dao.DAOException;
import io.github.Leandro208.projetoESIG.dao.VencimentoDao;
import io.github.Leandro208.projetoESIG.dominio.Vencimento;

public class VencimentoService{
	
	public List<SelectItem> getComboVencimentos(Vencimento vencimento) {
		VencimentoDao dao = new VencimentoDao();
		List<SelectItem> itensComboBoxVencimento = new ArrayList<>();
		List<Vencimento> vencimentos = dao.findAllVencimentos();
		for (Vencimento v : vencimentos) {
			boolean isSelecionado = vencimento != null && vencimento.getId() != null && vencimento.equals(v);
			itensComboBoxVencimento.add(new SelectItem(v, v.getDescricao(), null, isSelecionado));

		}
		return itensComboBoxVencimento;
	}

	public Vencimento buscarPorId(Long id) {
		VencimentoDao dao = new VencimentoDao();
		Vencimento vencimento = new Vencimento();
		try {
			vencimento = dao.findById(id, Vencimento.class);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return vencimento;
	}



}
