package io.github.Leandro208.projetoESIG.dao;

import java.lang.reflect.Field;
import java.util.Collection;

import javax.persistence.EntityManager;

import io.github.Leandro208.projetoESIG.connection.ConnectionFactory;
import io.github.Leandro208.projetoESIG.entities.Base;

public class GenericDAOImpl implements GenericDAO{
	protected static final int INSERIR = 1;
	protected static final int ATUALIZAR = 2;
	protected static final int REMOVER = 3;
	
	private EntityManager em;

	protected EntityManager getSession() {
		if (em == null || !em.isOpen()) {
		    em = ConnectionFactory.getEntityManager();
		    if (em == null || !em.isOpen())  {
		        throw new IllegalStateException("EntityManager não foi inicializado corretamente.");
		    }
		}
		return em;
	}
	
	protected void changeOperation(Base entidade, int operacao) throws DAOException {

		try {
			switch (operacao) {
			case INSERIR:
				getSession().persist(entidade);
				break;
			case ATUALIZAR:
				getSession().merge(entidade);
				break;
			case REMOVER:
				getSession().remove(entidade);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	@Override
	public void create(Base entidade) throws DAOException {
		changeOperation(entidade, INSERIR);
	}

	@Override
	public void update(Base entidade) throws DAOException {
		changeOperation(entidade, ATUALIZAR);
	}

	
	@Override
	public void commit() {
	    try {
	        if (em == null || !em.isOpen()) {
	            em = ConnectionFactory.getEntityManager();
	            if (em == null || !em.isOpen()) {
	                throw new IllegalStateException("EntityManager não foi inicializado corretamente.");
	            }
	        }

	        if (!em.getTransaction().isActive()) {
	            em.getTransaction().begin();
	        }

	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em != null && em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    }
	}

	@Override
	public <T extends Base> void remove(Class<T> clazz, Base entidade) throws DAOException {
		 T obj = getSession().find(clazz, entidade.getId());
		 if (obj == null) {
	            throw new DAOException("Entidade com ID " + entidade.getId() + " não encontrada.");
	        }
		 changeOperation(obj, REMOVER);
	}
	
	@Override
	public <T extends Base> T findById(Long id, Class<T> clazz) throws DAOException {
		try {
			T entidade = (T) getSession().find(clazz, id);
			return entidade;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public <T> Collection<T> findAll(Class<T> clazz) throws DAOException {
		try {
	        return getSession()
	            .createQuery("SELECT e FROM " + clazz.getSimpleName() + " e", clazz)
	            .getResultList();
	    } catch (Exception e) {
	        throw new DAOException("Erro ao buscar todas as entidades de " + clazz.getSimpleName(), e);
	    }
	}
	
	@Override
	public <T extends Base> void updateField(Long id, Class<T> clazz, String fieldName, Object newValue) throws DAOException {
	    try {
	        EntityManager entityManager = getSession();
	        T entity = entityManager.find(clazz, id);
	        
	        if (entity == null) {
	            throw new DAOException("Entidade com ID " + id + " não encontrada.");
	        }
	        
	        Field field = clazz.getDeclaredField(fieldName);
	        field.setAccessible(true);
	        field.set(entity, newValue);
	        
	        entityManager.getTransaction().begin();
	        entityManager.merge(entity);
	        entityManager.getTransaction().commit();
	    }  catch (Exception e) {
	        throw new DAOException("Erro inesperado ao atualizar campo", e);
	    }
	}
	
	@Override
	public void executarSqlNativoTransacional(String sql) throws DAOException {
	    try {
	        EntityManager entityManager = getSession();
	        entityManager.getTransaction().begin();
	        entityManager.createNativeQuery(sql).executeUpdate();
	        entityManager.getTransaction().commit();
	    } catch (Exception e) {
	        em.getTransaction().rollback();
	        throw new DAOException("Erro ao executar SQL nativo com transação", e);
	    }
	}
	
	
}
