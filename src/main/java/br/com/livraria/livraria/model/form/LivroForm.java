package br.com.livraria.livraria.model.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.livraria.livraria.model.LivroDTO;

public class LivroForm {

	private String titulo;
	private String descricao;
	private String paginas;
	private String preco;
	private String dataLancamento;
	
	
	public LivroDTO getLivro() {
		LivroDTO livro = new LivroDTO();
		livro.setTitulo(getTitulo());
		livro.setDescricao(getDescricao());
		livro.setPaginas(Integer.parseInt(getPaginas()));
		
		livro.setDataLancamento(data(getDataLancamento()));
		
		String novoPreco = getPreco().replace(',', '.');
		livro.setPreco(Double.parseDouble(novoPreco));
		
		
		return livro;
	}
	
	private Calendar data(String data) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		try {
			Date date = format.parse(data);
			cal.setTime(date);
		} catch (ParseException e) {
			System.out.println(data);
			e.printStackTrace();
		}
		
		return cal;
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
	
	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}


	public String getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
}
