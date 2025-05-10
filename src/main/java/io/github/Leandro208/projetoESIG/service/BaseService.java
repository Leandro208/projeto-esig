package io.github.Leandro208.projetoESIG.service;

public interface BaseService<T> {
	public T buscarPorId(Long id);
	public void salvar(T obj);
	public void remover(T obj);
}
