package io.github.Leandro208.projetoESIG.services;

import io.github.Leandro208.projetoESIG.dao.GenericDao;
import io.github.Leandro208.projetoESIG.entities.Pessoa;

public class PessoaService implements BaseService<Pessoa> {

	private GenericDao<Pessoa> dao;
	
	public PessoaService() {
		dao = new GenericDao<Pessoa>();
	}
	
	@Override
	public Pessoa buscarPorId(Long id) {
		return dao.buscarPorId(Pessoa.class, id);
	}
	
	@Override
	public void salvar(Pessoa obj) {
		dao.salvar(obj);
	}

	@Override
	public void remover(Pessoa obj) {
		dao.remover(Pessoa.class, obj.getId());
	}
}
