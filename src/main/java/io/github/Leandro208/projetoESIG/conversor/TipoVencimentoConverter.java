package io.github.Leandro208.projetoESIG.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import io.github.Leandro208.projetoESIG.dominio.TipoVencimento;

@FacesConverter("tipoVencimentoConverter")
public class TipoVencimentoConverter implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		return TipoVencimento.valueOf(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null) {
			return null;
		}
		TipoVencimento vencimento = (TipoVencimento) value;
		return String.valueOf(vencimento.getCodigo());
	}
}
