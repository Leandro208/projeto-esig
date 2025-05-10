package io.github.Leandro208.projetoESIG.dominio;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vencimento implements Base{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_vencimento")
	private Long id;
	
	private String descricao;
	
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private TipoVencimento tipo;

	public Vencimento() {
	}

	public Vencimento(Long id, String descricao, BigDecimal valor, TipoVencimento tipo) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoVencimento getTipo() {
		return tipo;
	}

	public void setTipo(TipoVencimento tipo) {
		this.tipo = tipo;
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
		Vencimento other = (Vencimento) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
