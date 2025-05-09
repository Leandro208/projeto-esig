package io.github.Leandro208.projetoESIG.negocio;

public class PessoaCrudOperation  extends CadastroCrudOperation{
	@Override
	public void operar(Operacao operacao) {
		validate(operacao);
		if(operacao.getComando().equals(ListaComando.CADASTRO_PESSOA)) {
			criar(operacao);
		} else if(operacao.getComando().equals(ListaComando.ALTERAR_PESSOA)) {
			alterar(operacao);
		}
	}

	@Override
	public void validate(Operacao operacao) {
		
	}
}
