package br.com.livraria.livraria.model.dto;

public class LivroDTO {
	
	private Long id;
	private String titulo;
	private String descricao;
	private String paginas;
	private String dataLancamento;
	private String preco;
	private String sumarioPath;
	
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public void setPreco(String preco) {
		this.preco = preco;
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
	public String getSumarioPath() {
		return sumarioPath;
	}
	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long i) {
		this.id = i;
	}	
	
}
