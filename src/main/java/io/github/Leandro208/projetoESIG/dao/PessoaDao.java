package io.github.Leandro208.projetoESIG.dao;

import java.util.List;

import javax.persistence.Query;

import io.github.Leandro208.projetoESIG.dominio.Pessoa;
import io.github.Leandro208.projetoESIG.dto.FormBuscaDTO;

public class PessoaDao extends GenericDAOImpl{

	@SuppressWarnings("unchecked")
	public List<Pessoa> filter(FormBuscaDTO form){
		String sql = "select * from pessoa p ";
		sql += "where 1=1 ";
		if (form.getNome() != null && !form.getNome().isEmpty()) {
	        sql += "and p.nome ilike '%" + form.getNome() + "%' ";
	    }
	    
	    if (form.getCargo() != null && form.getCargo().getId() > 0) {
	        sql += "and p.id_cargo = " + form.getCargo().getId();
	    }
	    
	    sql += " order by p.nome";
		Query q = getSession().createNativeQuery(sql, Pessoa.class);
		return q.getResultList();
	}
}
