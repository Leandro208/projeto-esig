package io.github.Leandro208.projetoESIG.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import io.github.Leandro208.projetoESIG.entities.Cargo;
import io.github.Leandro208.projetoESIG.services.CargoService;

@FacesConverter("cargoConverter")
public class CargoConverter implements Converter{

	private final CargoService service;
	
	public CargoConverter() {
		service = new CargoService();
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return (Cargo) service.buscarPorId(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return ((Cargo) value).getId().toString();
	}

}
