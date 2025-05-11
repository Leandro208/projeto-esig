package io.github.Leandro208.projetoESIG.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import io.github.Leandro208.projetoESIG.dao.PessoaSalarioConsolidadoDao;
import io.github.Leandro208.projetoESIG.dominio.HistoricoCalculoSalario;
import io.github.Leandro208.projetoESIG.dominio.PessoaSalarioConsolidado;
import io.github.Leandro208.projetoESIG.dto.FormBuscaDTO;
import io.github.Leandro208.projetoESIG.dto.RelatorioPessoaSalarioDTO;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PessoaSalarioConsolidadoService {

	public void calcular() {
		PessoaSalarioConsolidadoDao dao = new PessoaSalarioConsolidadoDao();
		dao.resetCalculoSalario();
		dao.recalcularSalario();
	}

	public List<PessoaSalarioConsolidado> consultar(FormBuscaDTO form) {
		PessoaSalarioConsolidadoDao dao = new PessoaSalarioConsolidadoDao();
		return dao.filter(form);
	}
	
	public void gerarRelatorioSalario(List<RelatorioPessoaSalarioDTO> dados, HttpServletResponse response) throws Exception {
		InputStream relatorioStream = getClass().getResourceAsStream("/relatorio/relatorio_pessoas.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(
			relatorioStream,
			new HashMap<>(),
			new JRBeanCollectionDataSource(dados)
		);

		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=relatorio-salario.pdf");

		try (ServletOutputStream out = response.getOutputStream()) {
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);
		}
	}
	
	public HistoricoCalculoSalario findUltimoCalculo() {
		PessoaSalarioConsolidadoDao dao = new PessoaSalarioConsolidadoDao();
		return dao.findUltimoCalculo();
	}
}
