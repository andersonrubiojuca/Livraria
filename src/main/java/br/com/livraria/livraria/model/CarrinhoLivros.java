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

import br.com.livraria.livraria.model.dto.LivroDTO;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, 
				proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CarrinhoLivros implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<LivroDTO> itens = new ArrayList<>();
	
	private Date date;

	public void add(LivroDTO livroq) {
		itens.add(livroq);
	}
	
	public ArrayList<LivroDTO> getItens(){
		return itens;
	}

	public String getTotal() {
		BigDecimal bc = new BigDecimal("0");
		
		for(LivroDTO livroq : itens) {
			bc = bc.add(new BigDecimal(livroq.getPrecoRaw()));
		}
		
		return NumberFormat.getCurrencyInstance(new Locale("pt", "br")).format(bc);
	}
	
	//mecher nisso
	public void remover(Long produtoId) {
		LivroDTO produto = new LivroDTO(produtoId);
		itens.remove(0);
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
