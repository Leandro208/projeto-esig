package io.github.Leandro208.projetoESIG.service;

import java.util.List;

import io.github.Leandro208.projetoESIG.dao.PessoaDao;
import io.github.Leandro208.projetoESIG.dominio.Pessoa;
import io.github.Leandro208.projetoESIG.dto.FormBuscaDTO;

public class PessoaService {
	
	public List<Pessoa> consultar(FormBuscaDTO form){
		PessoaDao dao = new PessoaDao();
		return dao.filter(form);
	}
	
	public List<Pessoa> buscarTodos(){
		return consultar(new FormBuscaDTO());
	}

}
