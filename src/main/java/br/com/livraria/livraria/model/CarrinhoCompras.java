package br.com.livraria.livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, 
				proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CarrinhoCompras implements Serializable{

	private static final long serialVersionUID = 1L;

	private Map<Livro, Long> itens = new LinkedHashMap<>();
	
	public Collection<Livro> getItens(){
		return itens.keySet();
	}
	
	public void add(Livro item) {
		itens.put(item, getQuantidade(item) + 1);
	}
	
	public Long getQuantidade(Livro item) {
		return this.itens.values().stream().reduce((long) 0, 
				(proximo, acumulador) -> proximo + acumulador);
	}
	
	public BigDecimal getTotal(Livro item) {
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		
		for (Livro item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total ;
	}

	public void remover(Long produtoId) {
		Livro produto = new Livro();
		produto.setId(produtoId);
		this.itens.remove(produto);
	}

	public void limpa() {
		this.itens.clear();
	}
}
