package io.github.Leandro208.projetoESIG.negocio;

import io.github.Leandro208.projetoESIG.entities.Base;

public interface Operacao {
	public Comando getComando();
	public void setComando(Comando comando);
	public Base getEntidade();
	public void setEntidade(Base entidade);
}
