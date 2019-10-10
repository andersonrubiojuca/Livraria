package br.com.livraria.livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class CarrinhoLivros implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<LivroQuant> itens = new ArrayList<>();
	
	private Date date;

	public void add(LivroQuant livroq) {
		itens.add(livroq);
	}

	public BigDecimal getTotal() {
		BigDecimal bc = new BigDecimal("0");
		
		for(LivroQuant livroq : itens) {
			bc.add(livroq.getTotal());
		}
		
		return bc;
	}
	
	public void remover(Long produtoId) {
		LivroQuant produto = new LivroQuant();
		produto.setId(produtoId);
		this.itens.remove(produto);
	}
	
	public void limpa() {
		this.itens.clear();
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	
	@Override
	public String toString() {
		return "CarrinhoLivros [itens=" + itens + ", date=" + date + "]";
	}
	
}
