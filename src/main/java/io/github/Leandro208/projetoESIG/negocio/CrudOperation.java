package io.github.Leandro208.projetoESIG.negocio;

public interface CrudOperation {
	public void operar(Operacao operacao);
	public void validate(Operacao operacao);
}
