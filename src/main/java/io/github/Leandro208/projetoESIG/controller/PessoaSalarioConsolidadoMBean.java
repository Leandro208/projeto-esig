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
import io.github.Leandro208.projetoESIG.dto.FormBuscaDTO;
import io.github.Leandro208.projetoESIG.dto.RelatorioPessoaSalarioDTO;
import io.github.Leandro208.projetoESIG.service.PessoaSalarioConsolidadoService;

@ManagedBean
@SessionScoped
public class PessoaSalarioConsolidadoMBean extends AbstractMBean {

	private PessoaSalarioConsolidadoService service;
	
	private HistoricoCalculoSalario ultimoCalculo;
	
	private List<PessoaSalarioConsolidado> lista;
	
	private final String INICIO = "index";
	
	private boolean emProcessamento = false;
	private boolean processado = false;
	
	private FormBuscaDTO formBusca;


	public PessoaSalarioConsolidadoMBean() {
		service = new PessoaSalarioConsolidadoService();
		reset();
	}
	
	private void reset() {
		formBusca = new FormBuscaDTO();
		carregarLista();
		carregarUltimoCalculo();
	}
	public String inicio() {
		reset();
		return navegar(INICIO);
	}
	
	private void carregarLista() {
		List<PessoaSalarioConsolidado> dados = service.consultar(formBusca);
		lista = dados != null ? dados : new ArrayList<>();
	}

	public String buscar() {
		carregarLista();
		return navegar(INICIO);
	}
	
	public void calcular() {
		emProcessamento = true;
		processado = false;

		new Thread(() -> {
			try {
				service.calcular();
				carregarLista();
				carregarUltimoCalculo();
			} finally {
				emProcessamento = false;
				processado = true;
			}
		}).start();
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
		if(ultimoCalculo!=null) {
			processado = true;
		}
	}
	
	public Collection<PessoaSalarioConsolidado> getLista() {
		return lista;
	}

	public HistoricoCalculoSalario getUltimoCalculo() {
		return ultimoCalculo;
	}
	
	public boolean isEmProcessamento() {
		return emProcessamento;
	}

	public boolean isProcessado() {
		return processado;
	}

	public FormBuscaDTO getFormBusca() {
		return formBusca;
	}

	public void setFormBusca(FormBuscaDTO formBusca) {
		this.formBusca = formBusca;
	}
	


}
