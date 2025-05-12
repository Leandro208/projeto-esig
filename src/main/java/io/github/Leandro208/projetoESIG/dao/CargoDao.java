package io.github.Leandro208.projetoESIG.dao;

import java.util.List;

import javax.persistence.Query;

import io.github.Leandro208.projetoESIG.dominio.Cargo;

public class CargoDao extends GenericDAOImpl {

	@SuppressWarnings("unchecked")
	public List<Cargo> findAllCargos() {
		String sql = "select * from cargo order by nome";
		Query query = getSession().createNativeQuery(sql.toString(), Cargo.class);
		return query.getResultList();
	}
}
