package io.github.Leandro208.projetoESIG.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.github.Leandro208.projetoESIG.dao.GenericDao;
import io.github.Leandro208.projetoESIG.dominio.Cargo;

public class CargoService implements BaseService<Cargo>{

	private GenericDao<Cargo> dao;
	
	public CargoService() {
		dao = new GenericDao<Cargo>();
	}
	@Override
	public Cargo buscarPorId(Long id) {
		return dao.buscarPorId(Cargo.class, id);
	}

	@Override
	public void salvar(Cargo obj) {
		dao.salvar(obj);
	}

	@Override
	public void remover(Cargo obj) {
		dao.remover(Cargo.class, obj.getId());
	}
	
	public List<Cargo> buscarTodos(){
		return dao.buscarTodos(Cargo.class);
	}

}
