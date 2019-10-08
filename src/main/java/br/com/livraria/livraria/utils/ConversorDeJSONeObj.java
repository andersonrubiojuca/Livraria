package br.com.livraria.livraria.utils;

import com.google.gson.Gson;

import br.com.livraria.livraria.model.CarrinhoLivros;

public class ConversorDeJSONeObj {

	Gson gson = new Gson();
	
	public String toJSON(CarrinhoLivros carrinho) {
		return gson.toJson(carrinho);
	}
	
	public CarrinhoLivros toCarrinhoLivros(String json) {
		return gson.fromJson(json, CarrinhoLivros.class);
	}
}
