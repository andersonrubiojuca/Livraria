package br.com.livraria.livraria.utils;

import com.google.gson.Gson;

import br.com.livraria.livraria.model.CarrinhoLivros;
import br.com.livraria.livraria.model.CompraReq;

public class ConversorDeJSONeObj {

	private Gson gson = new Gson();
	
	
	public String toJSON(CarrinhoLivros carrinho) {
		return gson.toJson(carrinho, CarrinhoLivros.class);
	}
	
	public String toJSON(CompraReq envio) {
		return gson.toJson(envio, CompraReq.class);
	}
	
	public CarrinhoLivros toCarrinhoLivros(String json) {
		return gson.fromJson(json, CarrinhoLivros.class);
	}
}
