package br.com.livraria.livraria.model.dto;

public class TokenDTO {
	
	private String token;
	private String string;
	
	public String getToken() {
		return token;
	}
	public String getString() {
		return string;
	}
	public TokenDTO(String token, String string) {
		this.token = token;
		this.string = string;
	}
	
	

}
