package br.com.livraria.livraria.model;

public class CompraReq {
	private String nome;
	private String email;
	private String conta;
	private String agencia;
	private Banco banco;
	
	private String total;
	
	public CompraReq (CompraEnvio envio) {
		this.nome = envio.getNome();
		this.email = envio.getEmail();
		this.conta = envio.getConta();
		this.agencia = envio.getAgencia();
		this.banco = envio.getBanco();
		
		this.total = envio.getCarrinho().getTotal();
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

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CompraReq [nome=" + nome + ", email=" + email + ", conta=" + conta + ", agencia=" + agencia + ", banco="
				+ banco + ", total=" + total + "]";
	}
	
	
}
