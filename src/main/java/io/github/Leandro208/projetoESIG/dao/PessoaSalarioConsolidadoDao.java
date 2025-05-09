package io.github.Leandro208.projetoESIG.dao;

import java.util.List;

import javax.persistence.Query;

import io.github.Leandro208.projetoESIG.entities.PessoaSalarioConsolidado;

public class PessoaSalarioConsolidadoDao extends GenericDAOImpl {

	public void resetCalculoSalario() {
		String sql = "DELETE FROM pessoa_salario_consolidado";
		try {
			executarSqlNativoTransacional(sql);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public void recalcularSalario() {
		String sql = "INSERT INTO pessoa_salario_consolidado (id_pessoa, id_cargo, salario) ";
		sql += "SELECT p.id_pessoa, c.id_cargo, ";
		sql += " SUM(CASE ";
		sql += " WHEN v.tipo = 'CREDITO' THEN v.valor ";
		sql += " WHEN v.tipo = 'DEBITO' THEN -v.valor ";
		sql += " ELSE 0 ";
		sql += " END) ";
		sql += "FROM pessoa p ";
		sql += "JOIN cargo c ON p.id_cargo = c.id_cargo ";
		sql += "JOIN cargo_vencimento cv ON c.id_cargo = cv.id_cargo ";
		sql += "JOIN vencimento v ON cv.id_vencimento = v.id_vencimento ";
		sql += "GROUP BY p.id_pessoa, c.id_cargo";

		try {
			executarSqlNativoTransacional(sql);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PessoaSalarioConsolidado> buscarTodos() {
		String sql = "select * from pessoa_salario_consolidado ps ";
		sql += "join pessoa p on p.id_pessoa = ps.id_pessoa ";
		sql += "order by p.nome";
		Query q = getSession().createNativeQuery(sql, PessoaSalarioConsolidado.class);
		return q.getResultList();
	}
}
