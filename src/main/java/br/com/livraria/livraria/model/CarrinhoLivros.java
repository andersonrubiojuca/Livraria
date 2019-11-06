package br.com.livraria.livraria.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import br.com.livraria.livraria.model.dto.LivroDTO;

@Entity
@Table(name = "carrinho")
@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, 
				proxyMode=ScopedProxyMode.TARGET_CLASS)
public class CarrinhoLivros implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<LivroDTO> itens = new ArrayList<>();
	
	private Date date;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne(mappedBy = "carrinho", fetch = FetchType.EAGER)
	private Compras compras;

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
	
	public void remover(int posicao) {
		itens.remove(posicao);
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
	

	public Compras getCompras() {
		return compras;
	}

	public void setCompras(Compras compras) {
		this.compras = compras;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setItens(ArrayList<LivroDTO> itens) {
		this.itens = itens;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CarrinhoLivros [itens=" + itens + ", date=" + date + "]";
	}
	
	
}
