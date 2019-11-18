package br.com.livraria.livraria.model;

public class CarrinhoLivrosEmail extends CarrinhoLivros{
	
	private static final long serialVersionUID = 1L;

	public CarrinhoLivrosEmail(CarrinhoLivros carrinho) {
		this.id = carrinho.getId();
		this.itens = carrinho.getItens();
		this.date = carrinho.getDate();
	}
}
