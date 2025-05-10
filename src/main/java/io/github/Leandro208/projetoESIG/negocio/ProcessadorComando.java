package io.github.Leandro208.projetoESIG.negocio;

public interface ProcessadorComando {
	public void execute(Movimento movimento);
	public void validate(Movimento movimento);
}
