package io.github.Leandro208.projetoESIG.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import io.github.Leandro208.projetoESIG.dto.RelatorioPessoaSalarioDTO;
import io.github.Leandro208.projetoESIG.entities.PessoaSalarioConsolidado;
import io.github.Leandro208.projetoESIG.services.PessoaSalarioConsolidadoService;

@ManagedBean
@SessionScoped
public class PessoaSalarioConsolidadoMBean extends AbstractMBean {

	private PessoaSalarioConsolidadoService service;

	private List<PessoaSalarioConsolidado> lista;
	
	private final String INICIO = "index";

	public PessoaSalarioConsolidadoMBean() {
		service = new PessoaSalarioConsolidadoService();
		carregarLista();
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

	public Collection<PessoaSalarioConsolidado> getLista() {
		return lista;
	}

}
