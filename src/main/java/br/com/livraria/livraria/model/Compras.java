package br.com.livraria.livraria.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 
 * @author Anderson
 * Este recurso eu não trabalharei a fundo, pois não é o objetivo do projeto, somente o manterei no BD
 */
@Entity
@Table(name = "compras")
public class Compras implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String email;
	
	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "carrinho_id", referencedColumnName="id")
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
