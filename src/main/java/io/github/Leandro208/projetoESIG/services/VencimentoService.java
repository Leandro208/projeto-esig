package io.github.Leandro208.projetoESIG.services;

import java.util.List;

import io.github.Leandro208.projetoESIG.dao.GenericDao;

import io.github.Leandro208.projetoESIG.entities.Vencimento;

public class VencimentoService implements BaseService<Vencimento> {

	private final GenericDao<Vencimento> dao;
	
	public VencimentoService() {
		dao = new GenericDao<Vencimento>();
	}
	
	@Override
	public Vencimento buscarPorId(Long id) {
		return dao.buscarPorId(Vencimento.class, id);
	}

	@Override
	public void salvar(Vencimento obj) {
		dao.salvar(obj);
		
	}

	@Override
	public void remover(Vencimento obj) {
		dao.remover(Vencimento.class, obj.getId());
		
	}

	public List<Vencimento> buscarTodos() {
		return dao.buscarTodos(Vencimento.class);
	}

}
