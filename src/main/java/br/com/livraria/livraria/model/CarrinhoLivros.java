package br.com.livraria.livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, 
				proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CarrinhoLivros implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<LivroQuant> itens = new ArrayList<>();
	
	private Date date;

	public void add(LivroQuant livroq) {
		itens.add(livroq);
	}
	
	public ArrayList<LivroQuant> getItens(){
		return itens;
	}

	public String getTotal() {
		BigDecimal bc = new BigDecimal("0");
		
		for(LivroQuant livroq : itens) {
			bc = bc.add(livroq.getTotal());
		}
		
		return NumberFormat.getCurrencyInstance(new Locale("pt", "br")).format(bc);
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
	
}
