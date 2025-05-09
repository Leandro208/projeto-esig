package io.github.Leandro208.projetoESIG.bean;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import io.github.Leandro208.projetoESIG.dto.RelatorioPessoaSalarioDTO;
import io.github.Leandro208.projetoESIG.entities.PessoaSalarioConsolidado;
import io.github.Leandro208.projetoESIG.services.PessoaSalarioConsolidadoService;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@SessionScoped
public class PessoaSalarioConsolidadoMBean extends AbstractMBean {

	private PessoaSalarioConsolidadoService service;

	private List<PessoaSalarioConsolidado> lista;

	public PessoaSalarioConsolidadoMBean() {
		service = new PessoaSalarioConsolidadoService();
		lista = service.buscarTodos() == null ? new ArrayList<PessoaSalarioConsolidado>() : service.buscarTodos();
	}

	public void calcular() {
		service.calcular();
		lista = service.buscarTodos();
	}

	public void gerarRelatorio() {
		try {

			List<PessoaSalarioConsolidado> dados = service.buscarTodos();
			List<RelatorioPessoaSalarioDTO> relatorioDados = dados.stream()
					.map(dado -> new RelatorioPessoaSalarioDTO(dado.getPessoa().getNome(), dado.getCargo().getNome(),
							dado.getSalario()))
					.collect(Collectors.toList());

			InputStream relatorioStream = getClass().getResourceAsStream("/relatorio/relatorio_pessoas.jasper");

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(relatorioDados);

			JasperPrint jasperPrint = JasperFillManager.fillReport(relatorioStream, new HashMap<>(), dataSource);

			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=relatorio-salario.pdf");

			ServletOutputStream out = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, out);

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Collection<PessoaSalarioConsolidado> getLista() {
		return lista;
	}

}
