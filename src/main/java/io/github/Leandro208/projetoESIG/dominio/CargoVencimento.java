package io.github.Leandro208.projetoESIG.dominio;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cargo_vencimento")
public class CargoVencimento implements Base {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cargo_vencimento")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_cargo")
	private Cargo cargo;
	
	@ManyToOne
	@JoinColumn(name = "id_vencimento")
	private Vencimento vencimento;
	
	public CargoVencimento() {

	}

	public CargoVencimento(Long id, Cargo cargo, Vencimento vencimento) {
		super();
		this.id = id;
		this.cargo = cargo;
		this.vencimento = vencimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Vencimento getVencimento() {
		return vencimento;
	}

	public void setVencimento(Vencimento vencimento) {
		this.vencimento = vencimento;
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
		CargoVencimento other = (CargoVencimento) obj;
		return Objects.equals(id, other.id);
	}
	
}
