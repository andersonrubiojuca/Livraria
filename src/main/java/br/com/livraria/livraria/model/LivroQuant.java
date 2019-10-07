package br.com.livraria.livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class LivroQuant implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Livro livro;
	private short quantidade;
	private Long id;
	

	public LivroQuant(Livro livro) {
		this.livro = livro;
		this.id = livro.getId();
		this.quantidade = 1;
	}
	
	public LivroQuant() {}
	
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public short getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(short quantidade) {
		this.quantidade = quantidade;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public BigDecimal getTotal() {
		BigDecimal bc = new BigDecimal(this.livro.getPreco());
		bc.multiply(new BigDecimal(this.quantidade));
		return bc;		
	}
}
