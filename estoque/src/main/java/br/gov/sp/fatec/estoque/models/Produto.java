package br.gov.sp.fatec.estoque.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name="produto")
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	
	private String fornecedor;
	
	private int periodo_validade;
	
	private int temp_armazemnagem;
	
	public Produto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public int getPeriodo_validade() {
		return periodo_validade;
	}

	public void setPeriodo_validade(int periodo_validade) {
		this.periodo_validade = periodo_validade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getTemp_armazenagem() {
		return temp_armazemnagem;
	}

	public void setTemp_armazenagem(int temp_armazenagem) {
		this.temp_armazemnagem = temp_armazenagem;
	}
	
	
	
}

