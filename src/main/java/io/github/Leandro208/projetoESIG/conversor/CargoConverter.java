package io.github.Leandro208.projetoESIG.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import io.github.Leandro208.projetoESIG.dominio.Cargo;
import io.github.Leandro208.projetoESIG.service.CargoService;

@FacesConverter("cargoConverter")
public class CargoConverter implements Converter{

	private final CargoService service;
	
	public CargoConverter() {
		service = new CargoService();
	}
	
	 @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	        if (value == null || value.trim().isEmpty()) {
	            return null;
	        }

	        try {
	            Long id = Long.valueOf(value);
	            return service.buscarPorId(id);
	        } catch (NumberFormatException e) {
	            return null;
	        }
	    }

	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	        if (value == null) {
	            return "";
	        }

	        if (value instanceof Cargo) {
	            Cargo cargo = (Cargo) value;
	            return cargo.getId() != null ? cargo.getId().toString() : "";
	        }

	        return "";
	    }

}
