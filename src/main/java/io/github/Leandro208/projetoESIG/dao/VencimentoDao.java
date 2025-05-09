package io.github.Leandro208.projetoESIG.dao;

import java.util.List;

import javax.persistence.Query;

import io.github.Leandro208.projetoESIG.entities.Vencimento;

public class VencimentoDao extends GenericDAOImpl{

	@SuppressWarnings("unchecked")
	public List<Vencimento> findAllVencimentos() {
		String sql = "select * from vencimento order by descricao";
		Query query = getSession().createNativeQuery(sql.toString(), Vencimento.class);
		return query.getResultList();
	}
}
