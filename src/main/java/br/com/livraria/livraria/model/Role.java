package br.com.livraria.livraria.model;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	@Deprecated
	public Role() {}
	
	public Role(String nome) {
		this.setNome(nome);
	}
	

	@Override
	public String getAuthority() {
		return this.nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
