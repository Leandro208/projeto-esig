package io.github.Leandro208.projetoESIG.negocio;

import io.github.Leandro208.projetoESIG.exception.NegocioException;

public interface ProcessadorComando {
	public void execute(Movimento movimento) throws NegocioException;
	public void validate(Movimento movimento);
}
