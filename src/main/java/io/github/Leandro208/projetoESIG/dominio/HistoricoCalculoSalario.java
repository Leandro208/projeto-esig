package io.github.Leandro208.projetoESIG.dominio;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "historico_calculo_salario")
public class HistoricoCalculoSalario implements Base{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_historico_calculo_salario")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cadastro")
	private Date dataCadastro;

	public HistoricoCalculoSalario(Long id, Date dataCadastro) {
		super();
		this.id = id;
		this.dataCadastro = dataCadastro;
	}
	
	public HistoricoCalculoSalario( Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public HistoricoCalculoSalario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
		HistoricoCalculoSalario other = (HistoricoCalculoSalario) obj;
		return Objects.equals(id, other.id);
	}
}
