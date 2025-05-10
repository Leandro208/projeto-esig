package io.github.Leandro208.projetoESIG.service;

import java.util.List;

import io.github.Leandro208.projetoESIG.dao.PessoaDao;
import io.github.Leandro208.projetoESIG.dominio.Pessoa;

public class PessoaService {
	
	public List<Pessoa> buscarTodos(){
		PessoaDao dao = new PessoaDao();
		return dao.findAllPessoa();
	}

}
