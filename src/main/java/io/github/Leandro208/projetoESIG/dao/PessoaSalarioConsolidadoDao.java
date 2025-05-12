package io.github.Leandro208.projetoESIG.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import io.github.Leandro208.projetoESIG.dominio.HistoricoCalculoSalario;
import io.github.Leandro208.projetoESIG.dominio.PessoaSalarioConsolidado;
import io.github.Leandro208.projetoESIG.dto.FormBuscaDTO;

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
		salvarHistorico();
	}
	
	private void salvarHistorico() {
		HistoricoCalculoSalario historico = new HistoricoCalculoSalario();
		historico.setDataCadastro(new Date());
		try {
			create(historico);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		finally {
			commit();
		}
	}

	@SuppressWarnings("unchecked")
	public List<PessoaSalarioConsolidado> filter(FormBuscaDTO form) {
	    String sql = "select * from pessoa_salario_consolidado ps ";
	    sql += "join pessoa p on p.id_pessoa = ps.id_pessoa ";
	    sql += "where 1=1 ";
	    
	    if (form.getNome() != null && !form.getNome().isEmpty()) {
	        sql += "and p.nome ilike '%" + form.getNome() + "%' ";
	    }
	    
	    if (form.getCargo() != null && form.getCargo().getId() > 0) {
	        sql += "and p.id_cargo = " + form.getCargo().getId();
	    }
	    
	    sql += " order by p.nome";
	    
	    Query q = getSession().createNativeQuery(sql, PessoaSalarioConsolidado.class);
	    return q.getResultList();
	}

	
	public PessoaSalarioConsolidado findById(Long id) {
		try {
			return findById(id, PessoaSalarioConsolidado.class);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public HistoricoCalculoSalario findUltimoCalculo() {
	    String sql = "SELECT * FROM historico_calculo_salario hcs ORDER BY hcs.data_cadastro DESC";
	    
	    Query q = getSession().createNativeQuery(sql, HistoricoCalculoSalario.class);
	    q.setMaxResults(1);
	    
	    try {
	        return (HistoricoCalculoSalario) q.getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}
}
