package br.gov.mt.mti.cidades;

import javax.persistence.Cacheable;
import javax.persistence.*;

@Entity
@Table(name = "estados")
@NamedQuery(name = "Estado.findAll", query = "SELECT f FROM Estado f ORDER BY f.nome", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Estado {
	@Id
	@SequenceGenerator(name = "estadoSequence", sequenceName = "estado_id_seq", allocationSize = 1, initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estadoSequence")
	private Integer id;

	
	private String nome;
	@Column(length = 2)
	private String sigla;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
