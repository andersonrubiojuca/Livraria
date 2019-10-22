package br.com.livraria.livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.livraria.livraria.model.dto.LivroDTO;

public class LivroQuant implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LivroDTO livro;
	private short quantidade;
	private Long id;
	

	public LivroQuant(Livro livro) {
		this.id = livro.getId();
		this.livro = new LivroDTO(livro);
		this.quantidade = 1;
	}
	
	public LivroQuant() {}
	
	public LivroDTO getLivro() {
		return livro;
	}
	public void setLivro(LivroDTO livro) {
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
		BigDecimal bc = new BigDecimal(this.livro.getPrecoRaw());
		bc.multiply(new BigDecimal(this.quantidade));
		return bc;		
	}
	
	@Override
	public String toString() {
		return "LivroQuant [livro=" + livro + ", quantidade=" + quantidade + ", id=" + id + "]";
	}
	
}
