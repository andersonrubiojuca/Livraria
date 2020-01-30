package br.com.livraria.livraria.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.livraria.livraria.model.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long>{
	
}
