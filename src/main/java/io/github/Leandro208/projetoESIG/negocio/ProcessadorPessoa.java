package io.github.Leandro208.projetoESIG.negocio;

import io.github.Leandro208.projetoESIG.dao.PessoaSalarioConsolidadoDao;
import io.github.Leandro208.projetoESIG.dominio.Pessoa;
import io.github.Leandro208.projetoESIG.dominio.PessoaSalarioConsolidado;

public class ProcessadorPessoa  extends ProcessadorCadastro{
	
	@Override
	public void execute(Movimento movimento) {
		validate(movimento);
		if(movimento.getComando().equals(ListaComando.CADASTRO_PESSOA)) {
			criar(movimento);
		} else if(movimento.getComando().equals(ListaComando.ALTERAR_PESSOA)) {
			alterar(movimento);
		} else if(movimento.getComando().equals(ListaComando.REMOVER_PESSOA)) {
			prepararRemocao(movimento);
		}
	}

	private void prepararRemocao(Movimento movimento) {
		PessoaSalarioConsolidadoDao pscDao = new PessoaSalarioConsolidadoDao();
		Pessoa pessoa = (Pessoa) movimento.getEntidade();
		PessoaSalarioConsolidado pessoaSalario = pscDao.findById(pessoa.getId());
		if(pessoaSalario != null && pessoaSalario.getId() != null) {
			movimento.setEntidade(pessoaSalario);
			remover(movimento);
			movimento.setEntidade(pessoa);
		}
		remover(movimento);
	}

	@Override
	public void validate(Movimento movimento) {
		
	}
}
