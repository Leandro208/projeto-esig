package io.github.Leandro208.projetoESIG.dao;

import java.util.Collection;

import io.github.Leandro208.projetoESIG.entities.Base;

public interface GenericDAO {
	public void create(Base entidade) throws DAOException;

	public void update(Base entidade) throws DAOException;

	void remove(Base entidade) throws DAOException;

	public <T extends Base> T findById(Long id, Class<T> clazz) throws DAOException;

	public <T> Collection<T> findAll(Class<T> clazz) throws DAOException;

	void commit();

	<T extends Base> void updateField(Long id, Class<T> clazz, String fieldName, Object newValue) throws DAOException;

	void executarSqlNativoTransacional(String sql) throws DAOException;
}
