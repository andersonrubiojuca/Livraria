package br.com.livraria.livraria.utils;

import com.google.gson.Gson;

import br.com.livraria.livraria.model.CarrinhoLivros;

public class ConversorDeJSONeObj {

	private Gson gson = new Gson();
	
	
	public String toJSON(CarrinhoLivros carrinho) {
		return gson.toJson(carrinho, CarrinhoLivros.class);
	}
	
	public CarrinhoLivros toCarrinhoLivros(String json) {
		return gson.fromJson(json, CarrinhoLivros.class);
	}
}
