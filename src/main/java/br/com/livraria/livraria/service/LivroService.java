package br.com.livraria.livraria.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.com.livraria.livraria.model.Livro;
import br.com.livraria.livraria.model.dto.LivroDTO;
import br.com.livraria.livraria.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	
	
	public void salvar(Livro livro) {
		repository.save(livro);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	public Optional<Livro> listarPorId(Long id) {
		Optional<Livro> OpLivro = repository.findById(id);
		return OpLivro;
	}
	
	
	
	public ArrayList<LivroDTO> listar(){
		Iterable<Livro> livros =  repository.findAll();
		ArrayList<LivroDTO> livrosDTO = new ArrayList<>();
		
		for(Livro livro : livros) 
			livrosDTO.add(new LivroDTO(livro));
		
		return livrosDTO;
	}
	
}
