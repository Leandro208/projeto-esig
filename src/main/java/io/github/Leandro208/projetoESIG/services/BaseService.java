package io.github.Leandro208.projetoESIG.services;

public interface BaseService<T> {
	public T buscarPorId(Long id);
	public void salvar(T obj);
	public void remover(T obj);
}
