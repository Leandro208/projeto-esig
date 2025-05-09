package io.github.Leandro208.projetoESIG.services;

import java.util.List;

import io.github.Leandro208.projetoESIG.dao.PessoaDao;
import io.github.Leandro208.projetoESIG.entities.Pessoa;

public class PessoaService {
	
	public List<Pessoa> buscarTodos(){
		PessoaDao dao = new PessoaDao();
		return dao.findAllPessoa();
	}

}
