package io.github.Leandro208.projetoESIG.dao;

import java.util.List;

import javax.persistence.Query;

import io.github.Leandro208.projetoESIG.entities.Pessoa;

public class PessoaDao extends GenericDAOImpl{

	@SuppressWarnings("unchecked")
	public List<Pessoa> findAllPessoa(){
		String sql = "select * from pessoa order by nome";
		Query q = getSession().createNativeQuery(sql, Pessoa.class);
		return q.getResultList();
	}
}
