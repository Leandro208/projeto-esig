package io.github.Leandro208.projetoESIG.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import io.github.Leandro208.projetoESIG.dominio.HistoricoCalculoSalario;
import io.github.Leandro208.projetoESIG.dominio.PessoaSalarioConsolidado;
import io.github.Leandro208.projetoESIG.dto.RelatorioPessoaSalarioDTO;
import io.github.Leandro208.projetoESIG.service.PessoaSalarioConsolidadoService;

@ManagedBean
@SessionScoped
public class PessoaSalarioConsolidadoMBean extends AbstractMBean {

	private PessoaSalarioConsolidadoService service;
	
	private HistoricoCalculoSalario ultimoCalculo;
	
	private List<PessoaSalarioConsolidado> lista;
	
	private final String INICIO = "index";

	public PessoaSalarioConsolidadoMBean() {
		service = new PessoaSalarioConsolidadoService();
		carregarLista();
		carregarUltimoCalculo();
	}
	
	public String inicio() {
		carregarLista();
		return navegar(INICIO);
	}
	
	private void carregarLista() {
		List<PessoaSalarioConsolidado> dados = service.buscarTodos();
		lista = dados != null ? dados : new ArrayList<>();
	}

	public void calcular() {
		service.calcular();
		carregarLista();
		carregarUltimoCalculo();
	}

	public void gerarRelatorio() {
		try {
			carregarLista();
			List<RelatorioPessoaSalarioDTO> relatorioDados = lista.stream()
					.map(dado -> new RelatorioPessoaSalarioDTO(
						dado.getPessoa().getNome(),
						dado.getCargo().getNome(),
						dado.getSalario()))
					.collect(Collectors.toList());


			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			
			service.gerarRelatorioSalario(relatorioDados, response);

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void carregarUltimoCalculo() {
		ultimoCalculo = service.findUltimoCalculo();
	}
	
	public Collection<PessoaSalarioConsolidado> getLista() {
		return lista;
	}

	public HistoricoCalculoSalario getUltimoCalculo() {
		return ultimoCalculo;
	}
	

}
