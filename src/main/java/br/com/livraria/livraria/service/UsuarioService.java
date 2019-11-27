package br.com.livraria.livraria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livraria.livraria.model.Usuario;
import br.com.livraria.livraria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Optional<Usuario> login(String login){
		return repository.findByLogin(login);
	}

	public Optional<Usuario> findById(Long idUsuario) {
		return repository.findById(idUsuario);
	}
}
