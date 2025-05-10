package io.github.Leandro208.projetoESIG.service;

import java.util.List;

import io.github.Leandro208.projetoESIG.dao.GenericDao;
import io.github.Leandro208.projetoESIG.dominio.Pessoa;
import io.github.Leandro208.projetoESIG.dominio.PessoaSalarioConsolidado;

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
		PessoaSalarioConsolidadoService service = new PessoaSalarioConsolidadoService();
		PessoaSalarioConsolidado pessoaSalario = service.buscarPorId(obj.getId());
		if(pessoaSalario != null) {
			service.remover(pessoaSalario.getId());
		}
		dao.remover(Pessoa.class, obj.getId());
	}

	public List<Pessoa> buscarTodos() {
		return dao.criarHqlBuscaLista("select p from Pessoa p order by p.nome");
	}
}
