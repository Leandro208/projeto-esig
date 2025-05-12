package io.github.Leandro208.projetoESIG.negocio;

import io.github.Leandro208.projetoESIG.dao.DAOException;
import io.github.Leandro208.projetoESIG.dao.GenericDAO;
import io.github.Leandro208.projetoESIG.dao.GenericDAOImpl;
import io.github.Leandro208.projetoESIG.dominio.Base;
import io.github.Leandro208.projetoESIG.exception.NegocioException;

public class ProcessadorCadastro implements ProcessadorComando{

	@Override
	public void execute(Movimento movimento) throws NegocioException {
		validate(movimento);
		if(movimento.getComando().equals(ListaComando.CADASTRAR)) {
			criar(movimento);
		} else if(movimento.getComando().equals(ListaComando.ALTERAR)) {
			alterar(movimento);
		} else if(movimento.getComando().equals(ListaComando.REMOVER)) {
			
		}
	}

	@Override
	public void validate(Movimento movimento) {

	}

	protected Object criar(Movimento movimento) {
		GenericDAO dao = new GenericDAOImpl();
		try {
			dao.create(movimento.getEntidade());
		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			dao.commit();
		}
		return movimento.getEntidade();
	}

	protected Object alterar(Movimento movimento) {
		GenericDAO dao = new GenericDAOImpl();
		try {
			dao.update(movimento.getEntidade());
		} catch (DAOException e) {
			e.printStackTrace();
		} finally {
			dao.commit();
		}
		return movimento.getEntidade();
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends Base> Object remover(Movimento movimento) {
	    GenericDAO dao = new GenericDAOImpl();
	    Class<T> clazz = (Class<T>) movimento.getEntidade().getClass();  
	    try {
	        dao.remove(clazz, movimento.getEntidade()); 
	    } catch (DAOException e) {
	        e.printStackTrace();
	    } finally {
	        dao.commit();
	    }
	    return movimento.getEntidade();
	}
}
