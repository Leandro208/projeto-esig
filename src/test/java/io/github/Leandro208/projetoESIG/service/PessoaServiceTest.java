package io.github.Leandro208.projetoESIG.service;

import io.github.Leandro208.projetoESIG.dao.PessoaDao;
import io.github.Leandro208.projetoESIG.dominio.Pessoa;
import io.github.Leandro208.projetoESIG.dto.FormBuscaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PessoaServiceTest {
	private PessoaDao pessoaDao;
	private PessoaService pessoaService;

	@BeforeEach
	public void setUp() {
		pessoaDao = mock(PessoaDao.class);
		pessoaService = new PessoaService() {
			@Override
			public List<Pessoa> consultar(FormBuscaDTO form) {
				return pessoaDao.filter(form);
			}
		};
	}

	@Test
	public void deveRetornarListaDePessoas() {
		// Arrange
		FormBuscaDTO form = new FormBuscaDTO();
		Pessoa p1 = new Pessoa();
		p1.setNome("Maria");
		Pessoa p2 = new Pessoa();
		p2.setNome("João");

		when(pessoaDao.filter(form)).thenReturn(Arrays.asList(p1, p2));

		List<Pessoa> resultado = pessoaService.consultar(form);

		assertEquals(2, resultado.size());
		assertEquals("Maria", resultado.get(0).getNome());
		assertEquals("João", resultado.get(1).getNome());

		verify(pessoaDao, times(1)).filter(form);
	}
}
