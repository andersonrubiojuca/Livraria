package br.com.livraria.livraria.model;

public enum TipoCapa {
	DEFAULT("default.jpg");
	
	private String generico;
	
	TipoCapa(String tipo){
		this.generico = tipo;
	}
	
	public String getGenerico() {
		return generico;
	}
}
