package br.com.livraria.livraria.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.livraria.livraria.model.Role;
import br.com.livraria.livraria.model.Usuario;
import br.com.livraria.livraria.repository.RoleRepository;
import br.com.livraria.livraria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Optional<Usuario> login(String login){
		return repository.findByLogin(login);
	}

	public Optional<Usuario> findById(Long idUsuario) {
		return repository.findById(idUsuario);
	}

	public UsuarioService(UsuarioRepository repository, RoleRepository roleRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.repository = repository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public void salvar(Usuario usuario) {
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        usuario.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        usuario.setRole(new HashSet<Role>(Arrays.asList(userRole)));
        repository.save(usuario);
    }
}
