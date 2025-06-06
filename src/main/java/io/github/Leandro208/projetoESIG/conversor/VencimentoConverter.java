package io.github.Leandro208.projetoESIG.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import io.github.Leandro208.projetoESIG.dominio.Vencimento;
import io.github.Leandro208.projetoESIG.service.VencimentoService;

@FacesConverter("vencimentoConverter")
public class VencimentoConverter implements Converter{

	private VencimentoService service;
	
	public VencimentoConverter() {
		service = new VencimentoService();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return (Vencimento) service.buscarPorId(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Vencimento) value).getId().toString();
	}

}
