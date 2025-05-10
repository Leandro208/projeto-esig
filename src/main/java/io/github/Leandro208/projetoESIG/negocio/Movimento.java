package io.github.Leandro208.projetoESIG.negocio;

import io.github.Leandro208.projetoESIG.dominio.Base;

public interface Movimento {
	public Comando getComando();
	public void setComando(Comando comando);
	public Base getEntidade();
	public void setEntidade(Base entidade);
}
