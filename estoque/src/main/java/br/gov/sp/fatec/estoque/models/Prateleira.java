package br.gov.sp.fatec.estoque.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity(name="prateleira")
public class Prateleira implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private long nota_fiscal;
    private Date data_nota_fiscal;
    private Date data_entrada;
    private Date data_fabricacao;
    private long numero_lote;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_produto")
    private Produto produto;
    
    
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Prateleira() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getNota_fiscal() {
		return nota_fiscal;
	}
	public void setNota_fiscal(long nota_fiscal) {
		this.nota_fiscal = nota_fiscal;
	}
	public Date getData_nota_fiscal() {
		return data_nota_fiscal;
	}
	public void setData_nota_fiscal(Date data_nota_fiscal) {
		this.data_nota_fiscal = data_nota_fiscal;
	}
	public Date getData_entrada() {
		return data_entrada;
	}
	public void setData_entrada(Date data_entrada) {
		this.data_entrada = data_entrada;
	}
	public Date getData_fabricacao() {
		return data_fabricacao;
	}
	public void setData_fabricacao(Date data_fabricacao) {
		this.data_fabricacao = data_fabricacao;
	}
	public long getNumero_lote() {
		return numero_lote;
	}
	public void setNumero_lote(long numero_lote) {
		this.numero_lote = numero_lote;
	}
    
    
}
