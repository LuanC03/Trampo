package br.com.ufcg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tab_avaliacao")
public class Avaliacao {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id_avaliacao")
	private Long id;
	
	@Column(name = "nota_avaliacao")
	private Double nota;
	
	
	public Avaliacao(Double nota) {
		this.nota = nota;
		
	}
	
	public Avaliacao() {
		
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getNota() {
		return this.nota;
	}
	
	public void setNota(Double nota) {
		this.nota = nota;
	}
	
	
}
