package io.github.Leandro208.projetoESIG.service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import io.github.Leandro208.projetoESIG.dao.CargoDao;
import io.github.Leandro208.projetoESIG.dao.DAOException;
import io.github.Leandro208.projetoESIG.dominio.Cargo;

public class CargoService {

	public List<SelectItem> getComboCargos(Cargo cargo) {
		CargoDao dao = new CargoDao();
		List<SelectItem> itensComboBoxCargos = new ArrayList<>();
		List<Cargo> cargos = dao.findAllCargos();
		for (Cargo c : cargos) {
			boolean isSelecionado = cargo != null && cargo.getId() != null && cargo.equals(c);
			itensComboBoxCargos.add(new SelectItem(c, c.getNome(), null, false, false,isSelecionado));

		}
		return itensComboBoxCargos;
	}

	public Cargo buscarPorId(Long id) {
		CargoDao dao = new CargoDao();
		Cargo cargo = new Cargo();
		try {
			cargo = dao.findById(id, Cargo.class);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return cargo;
	}
}
