package io.github.Leandro208.projetoESIG.service;

import io.github.Leandro208.projetoESIG.dao.GenericDao;
import io.github.Leandro208.projetoESIG.dominio.CargoVencimento;

public class CargoVencimentoService implements BaseService<CargoVencimento>{

	private final GenericDao<CargoVencimento> dao;
	
	public CargoVencimentoService() {
		dao = new GenericDao<CargoVencimento>();
	}
	
	@Override
	public CargoVencimento buscarPorId(Long id) {
		return dao.buscarPorId(CargoVencimento.class, id);
	}

	@Override
	public void salvar(CargoVencimento obj) {
		dao.salvar(obj);
		
	}

	@Override
	public void remover(CargoVencimento obj) {
		dao.remover(CargoVencimento.class, obj.getId());
		
	}

}
