package br.com.livraria.livraria.utils;

import org.jasypt.util.text.TextEncryptor;

import com.google.gson.Gson;

import br.com.livraria.livraria.model.CarrinhoLivros;

public class ConversorDeJSONeObj {

	private Gson gson = new Gson();
	
	private TextEncryptor textEncryptor;
	
	public String toJSON(CarrinhoLivros carrinho) {
		String jsonPuro = gson.toJson(carrinho);
		
		return textEncryptor.encrypt(jsonPuro);
	}
	
	public CarrinhoLivros toCarrinhoLivros(String json) {
		String jsonCript = json;
		String deCript = textEncryptor.decrypt(jsonCript);
		return gson.fromJson(deCript, CarrinhoLivros.class);
	}
}
