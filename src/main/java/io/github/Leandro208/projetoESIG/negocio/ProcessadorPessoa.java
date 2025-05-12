package io.github.Leandro208.projetoESIG.negocio;

import java.util.List;

import io.github.Leandro208.projetoESIG.dao.DAOException;
import io.github.Leandro208.projetoESIG.dao.PessoaDao;
import io.github.Leandro208.projetoESIG.dao.PessoaSalarioConsolidadoDao;
import io.github.Leandro208.projetoESIG.dominio.Pessoa;
import io.github.Leandro208.projetoESIG.dominio.PessoaSalarioConsolidado;
import io.github.Leandro208.projetoESIG.exception.NegocioException;
import io.github.Leandro208.projetoESIG.util.Criptografar;

public class ProcessadorPessoa  extends ProcessadorCadastro{
	
	@Override
	public void execute(Movimento movimento) throws NegocioException {
		validate(movimento);
		if(movimento.getComando().equals(ListaComando.CADASTRO_PESSOA)) {
			prepararCadastro(movimento);
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
	
	private void prepararCadastro(Movimento movimento) throws NegocioException {
		Pessoa pessoa = (Pessoa) movimento.getEntidade();
		
		PessoaDao dao = new PessoaDao();
		try {
			List<Pessoa> lista =  (List<Pessoa>) dao.findAll(Pessoa.class);
			for(Pessoa p : lista) {
				if(p.getUsuario().equals(pessoa.getUsuario())) {
					throw new NegocioException("Ja existe uma pessoa com esse usuario!");
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		String senhaEncriptografada = Criptografar.encriptografar(pessoa.getSenha());
		pessoa.setSenha(senhaEncriptografada);
		
		criar(movimento);
	}
	
	@Override
	public void validate(Movimento movimento) {
		
	}
}
