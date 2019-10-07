package br.com.livraria.livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, 
				proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable{

	private static final long serialVersionUID = 1L;

	private Map<LivroQuant, Long> itens = new LinkedHashMap<>();
	

	private Date date;
	
	public Set<LivroQuant> getItens(){
		return itens.keySet();
	}
	
	public void add(LivroQuant item) {
		itens.put(item, getQuantidade(item) + 1);
	}
	
	public Long getQuantidade(LivroQuant item) {
		return this.itens.values().stream().reduce((long) 0, 
				(proximo, acumulador) -> proximo + acumulador);
	}
	
	public BigDecimal getTotal(LivroQuant item) {
		return item.getTotal();
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for (LivroQuant item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total ;
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
