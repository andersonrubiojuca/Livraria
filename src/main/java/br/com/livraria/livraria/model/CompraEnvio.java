package br.com.livraria.livraria.model;

import com.google.gson.Gson;


public class CompraEnvio {
	
	private String nome;
	private String email;
	private String conta;
	private String agencia;
	private Banco banco;
	private CarrinhoLivros carrinho;
	
	public Compras getCompra() {
		Compras compra = new Compras();
		
		compra.setNome(nome);
		compra.setEmail(email);
		compra.setCarrinho(carrinho);
		
		return compra;
	}
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this, CompraEnvio.class);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConta() {
		return conta;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public String getAgencia() {
		return agencia;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public Banco getBanco() {
		return banco;
	}
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	public CarrinhoLivros getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(CarrinhoLivros carrinho) {
		this.carrinho = carrinho;
	}
	
}
