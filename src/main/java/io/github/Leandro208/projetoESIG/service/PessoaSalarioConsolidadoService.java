package io.github.Leandro208.projetoESIG.service;

import java.util.Date;
import java.util.List;

import io.github.Leandro208.projetoESIG.dao.GenericDao;
import io.github.Leandro208.projetoESIG.dominio.HistoricoCalculoSalario;
import io.github.Leandro208.projetoESIG.dominio.PessoaSalarioConsolidado;

public class PessoaSalarioConsolidadoService {

	private GenericDao<PessoaSalarioConsolidado> dao;

	public PessoaSalarioConsolidadoService() {
		dao = new GenericDao<PessoaSalarioConsolidado>();
	}
	
	public PessoaSalarioConsolidado buscarPorId(Long id) {
		return dao.buscarPorId(PessoaSalarioConsolidado.class, id);
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
		salvarHistorico();
	}
	
	private void salvarHistorico() {
		GenericDao<HistoricoCalculoSalario> historicoDao = new GenericDao<HistoricoCalculoSalario>();
		historicoDao.salvar(new HistoricoCalculoSalario(new Date()));
	}

	public List<PessoaSalarioConsolidado> buscarTodos(){
		return dao.criarHqlBuscaLista("select p from PessoaSalarioConsolidado p order by p.pessoa.nome");
	}
	
	public void remover(Long id) {
		dao.remover(PessoaSalarioConsolidado.class, id);
	}
	
	public HistoricoCalculoSalario findUltimoCalculo() {
		GenericDao<HistoricoCalculoSalario> historicoDao = new GenericDao<HistoricoCalculoSalario>();
		List<HistoricoCalculoSalario> lista = historicoDao.criarHqlBuscaLista("select h from HistoricoCalculoSalario h order by h.dataCadastro desc");
		if (lista.isEmpty()) {
	        return null; 
	    }
	    return lista.get(0);
	}
}
