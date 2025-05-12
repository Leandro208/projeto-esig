package io.github.Leandro208.projetoESIG.dto;

import io.github.Leandro208.projetoESIG.dominio.Cargo;

public class FormBuscaDTO {
	
	private Long id;
	private String nome;
	private Cargo cargo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
}
