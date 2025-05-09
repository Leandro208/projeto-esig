package io.github.Leandro208.projetoESIG.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_salario_consolidado")
public class PessoaSalarioConsolidado implements Base, Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "id_cargo")
	private Cargo cargo;
	
	private BigDecimal salario;
	
	public PessoaSalarioConsolidado() {
		super();
	}

	public PessoaSalarioConsolidado(Long id, Pessoa pessoa, Cargo cargo, BigDecimal salario) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.cargo = cargo;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaSalarioConsolidado other = (PessoaSalarioConsolidado) obj;
		return Objects.equals(id, other.id);
	}
	
}
