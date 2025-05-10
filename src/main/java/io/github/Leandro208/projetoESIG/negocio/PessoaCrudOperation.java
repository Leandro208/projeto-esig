package io.github.Leandro208.projetoESIG.negocio;

import io.github.Leandro208.projetoESIG.dao.PessoaSalarioConsolidadoDao;
import io.github.Leandro208.projetoESIG.dominio.Pessoa;
import io.github.Leandro208.projetoESIG.dominio.PessoaSalarioConsolidado;

public class PessoaCrudOperation  extends CadastroCrudOperation{
	
	@Override
	public void operar(Operacao operacao) {
		validate(operacao);
		if(operacao.getComando().equals(ListaComando.CADASTRO_PESSOA)) {
			criar(operacao);
		} else if(operacao.getComando().equals(ListaComando.ALTERAR_PESSOA)) {
			alterar(operacao);
		} else if(operacao.getComando().equals(ListaComando.REMOVER_PESSOA)) {
			prepararRemocao(operacao);
		}
	}

	private void prepararRemocao(Operacao operacao) {
		PessoaSalarioConsolidadoDao pscDao = new PessoaSalarioConsolidadoDao();
		Pessoa pessoa = (Pessoa) operacao.getEntidade();
		PessoaSalarioConsolidado pessoaSalario = pscDao.findById(pessoa.getId());
		if(pessoaSalario != null && pessoaSalario.getId() != null) {
			operacao.setEntidade(pessoaSalario);
			remover(operacao);
			operacao.setEntidade(pessoa);
		}
		remover(operacao);
	}

	@Override
	public void validate(Operacao operacao) {
		
	}
}
