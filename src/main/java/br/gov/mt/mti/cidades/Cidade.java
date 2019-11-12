package br.gov.mt.mti.cidades;

import javax.persistence.Cacheable;
import javax.persistence.*;

@Entity
@Table(name = "cidades")
@NamedQuery(name = "Cidade.findAll", query = "SELECT f FROM Cidade f ORDER BY f.nome", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Cidade {
	@Id
	@SequenceGenerator(name = "cidadeSequence", sequenceName = "cidade_id_seq", allocationSize = 1, initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidadeSequence")
	private Integer id;

	@Column(length = 120)
	private String nome;
	@Column(name = "codigo_ibge")
	private Integer codigoIbge;
	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;
	@Column(name = "populacao_2010")
	private Integer populacao2010;
	@Column(name = "densidade_demo")
	private Integer densidade;

	private String gentilico;

	private Integer area;

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

	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	
	public Integer getPopulacao2010() {
		return populacao2010;
	}

	public void setPopulacao2010(Integer populacao2010) {
		this.populacao2010 = populacao2010;
	}

	public Integer getDensidade() {
		return densidade;
	}

	public void setDensidade(Integer densidade) {
		this.densidade = densidade;
	}

	public String getGentilico() {
		return gentilico;
	}

	public void setGentilico(String gentilico) {
		this.gentilico = gentilico;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	

}
