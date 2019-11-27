package br.com.livraria.livraria.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.livraria.livraria.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	Optional<Usuario> findByLogin(String login);
}
