package br.com.livraria.livraria.model.dto;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import br.com.livraria.livraria.model.Livro;

public class LivroDTO {
	
	private Long id;
	private String titulo;
	private String descricao;
	private String paginas;
	private String dataLancamento;
	private String preco;
	private double precoRaw;
	private String sumarioPath;
	
	
	private final String generico = "default.jpg";
	
	public LivroDTO(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.descricao = livro.getDescricao();
		this.paginas = "" + livro.getPaginas();
		this.dataLancamento = dataLiquida(livro.getDataLancamento());
		this.preco = moeda(livro.getPreco());
		this.precoRaw = livro.getPreco();
		
		if(livro.getSumarioPath() == null) 
			this.sumarioPath = generico;
		 else 
			 this.sumarioPath = livro.getSumarioPath();
		
	}
	
	private String dataLiquida(Calendar cal) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		String data = format.format(cal.getTime());
		
		return data;
	}
	
	private String moeda(double moeda) {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "br")).format(moeda);
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getPaginas() {
		return paginas;
	}
	public String getDataLancamento() {
		return dataLancamento;
	}
	public String getPreco() {
		return preco;
	}
	
	public double getPrecoRaw() {
		return precoRaw;
	}
	
	public String getSumarioPath() {
		return sumarioPath;
	}
	public Long getId() {
		return id;
	}
	
}
