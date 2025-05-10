package io.github.Leandro208.projetoESIG.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import io.github.Leandro208.projetoESIG.connection.ConnectionFactory;
import io.github.Leandro208.projetoESIG.dominio.Base;
import io.github.Leandro208.projetoESIG.util.Message;

public class GenericDao<T extends Base> implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	public GenericDao() {
		this.manager = ConnectionFactory.getEntityManager();
	}

	public T buscarPorId(Class<T> clazz, Long id) {
		return manager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> criarHqlBuscaLista(String hql) {
		return manager.createQuery(hql).getResultList();
	}

	public List<T> buscarTodos(Class<T> clazz) {
		String hql = "FROM " + clazz.getSimpleName();
		return manager.createQuery(hql, clazz).getResultList();
	}

	public void salvar(T entidade) {
	    try {
	        manager.getTransaction().begin();
	        if (entidade.getId() == null) {
	            manager.persist(entidade);
	        } else {
	            manager.merge(entidade);
	        }
	        manager.getTransaction().commit();
	        Message.info("Operação realizada com sucesso!");
	    } catch (Exception e) {
	        if (manager.getTransaction().isActive()) {
	            manager.getTransaction().rollback();
	        }
	        e.printStackTrace(); // Para depuração
	    }
	}

	public void remover(Class<T> clazz, Long id) {
	    T t = buscarPorId(clazz, id);
	    try {
	        manager.getTransaction().begin();
	        manager.remove(t);
	        manager.getTransaction().commit();
	        Message.info("Excluido com sucesso!");
	    } catch (Exception e) {
	        if (manager.getTransaction().isActive()) {
	            manager.getTransaction().rollback();
	        }
	        e.printStackTrace(); // Para depuração
	    }
	}


	public void executeUpdate(String sql) {
		try {
			manager.getTransaction().begin();
			manager.createNativeQuery(sql).executeUpdate();
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

}
