package br.gov.sp.fatec.estoque.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "autorizacao")
public class Autorizacao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
    
    @Column(name = "nome", unique=true, length = 20, nullable = false)
    private String nome;
    
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

}
