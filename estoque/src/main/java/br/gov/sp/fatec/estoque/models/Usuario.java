package br.gov.sp.fatec.estoque.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity(name = "usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
    
	@Column(name = "username", unique=true, length = 20, nullable = false)
    private String username;
	
    @Column(name = "nome", unique=true, length = 20, nullable = false)
    private String nome;
    
    @Column(name = "senha", length = 50, nullable = false)
    private String senha;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UAU_USUARIO_AUTORIZACAO", 
    	joinColumns = { @JoinColumn(name = "user_id") }, 
    	inverseJoinColumns = { @JoinColumn(name = "aut_id") })
    private List<Autorizacao> autorizacoes;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the autorizacoes
	 */
	public List<Autorizacao> getAutorizacoes() {
		return autorizacoes;
	}

	/**
	 * @param autorizacoes the autorizacoes to set
	 */
	public void setAutorizacoes(List<Autorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

}
