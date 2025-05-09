package io.github.Leandro208.projetoESIG.services;

import java.util.List;

import io.github.Leandro208.projetoESIG.dao.GenericDao;
import io.github.Leandro208.projetoESIG.entities.PessoaSalarioConsolidado;

public class PessoaSalarioConsolidadoService {

	private GenericDao<PessoaSalarioConsolidado> dao;

	public PessoaSalarioConsolidadoService() {
		dao = new GenericDao<PessoaSalarioConsolidado>();
	}

	public void calcular() {
		String sql = "DELETE FROM pessoa_salario_consolidado";
		dao.executeUpdate(sql);
		
		 sql = "INSERT INTO pessoa_salario_consolidado (id_pessoa, id_cargo, salario) ";
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

		dao.executeUpdate(sql);
	}
	
	public List<PessoaSalarioConsolidado> buscarTodos(){
		return dao.buscarTodos(PessoaSalarioConsolidado.class);
	}
}
