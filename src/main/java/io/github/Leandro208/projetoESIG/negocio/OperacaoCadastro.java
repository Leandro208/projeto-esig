package io.github.Leandro208.projetoESIG.negocio;

import io.github.Leandro208.projetoESIG.dominio.Base;

public class OperacaoCadastro implements Operacao {
	private Base entidade;
	private Comando comando;

	@Override
	public Comando getComando() {
		return comando;
	}

	@Override
	public Base getEntidade() {
		return entidade;
	}

	@Override
	public void setComando(Comando comando) {
		this.comando = comando;
	}

	@Override
	public void setEntidade(Base entidade) {
		this.entidade = entidade;

	}
}
