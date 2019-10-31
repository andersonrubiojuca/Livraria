package br.com.livraria.livraria.model.form;

import br.com.livraria.livraria.model.Banco;
import br.com.livraria.livraria.model.CompraEnvio;

public class CompraForm {
	
	private String nome;
	private String email;
	private String conta;
	private String agencia;
	private String banco;
	
	public CompraEnvio getCompra() {
		CompraEnvio compra = new CompraEnvio();
		compra.setNome(nome);
		compra.setEmail(email);
		compra.setConta(conta);
		compra.setAgencia(agencia);
		
		switch(banco) {
			case "0":
				compra.setBanco(Banco.BANCO_DO_BRASIL);
				break;
			case "1":
				compra.setBanco(Banco.BRADESCO);
				break;
			case "2":
				compra.setBanco(Banco.CAIXA);
				break;
			case "3":
				compra.setBanco(Banco.CITIBANK);
				break;
			case "4":
				compra.setBanco(Banco.ITAU);
				break;
			case "5":
				compra.setBanco(Banco.SANTANDER);
				break;
			default:
				compra.setBanco(null);
		}
		
		return compra;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setConta(String conta) {
		this.conta = conta;
	}
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
}