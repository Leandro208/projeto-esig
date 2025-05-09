package io.github.Leandro208.projetoESIG.dto;

import java.math.BigDecimal;

public class RelatorioPessoaSalarioDTO {
	private String nomePessoa;
	private String nomeCargo;
	private BigDecimal salario;

	public RelatorioPessoaSalarioDTO() {
		
	}
	
	public RelatorioPessoaSalarioDTO(String nomePessoa, String nomeCargo, BigDecimal salario) {
		this.nomePessoa = nomePessoa;
		this.nomeCargo = nomeCargo;
		this.salario = salario;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

}
