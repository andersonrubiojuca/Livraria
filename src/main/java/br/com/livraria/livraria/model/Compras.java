package br.com.livraria.livraria.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author Anderson
 * Este recurso eu não trabalharei a fundo, pois não é o objetovo do projeto, somente o manterei no BD
 */
@Entity
public class Compras implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String email;
	private CarrinhoLivros carrinho;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public CarrinhoLivros getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(CarrinhoLivros carrinho) {
		this.carrinho = carrinho;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
