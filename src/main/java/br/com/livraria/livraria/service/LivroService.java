package br.com.livraria.livraria.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import java.text.NumberFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livraria.livraria.model.Livro;
import br.com.livraria.livraria.model.dto.LivroDTO;
import br.com.livraria.livraria.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;
	
	private final String generico = "default.jpg";

	
	public void salvar(Livro livro) {
		repository.save(livro);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
	public Livro listarPorId(Long id) {
		Livro livro = new Livro();
		Optional<Livro> OpLivro = repository.findById(id);
		if(OpLivro.isPresent()) {
			livro = OpLivro.get();
		}
		
		return livro;
	}
	
	
	public ArrayList<LivroDTO> listar(){
		ArrayList<Livro> livros = (ArrayList<Livro>) repository.findAll();
		ArrayList<LivroDTO> livrosDTO = new ArrayList<>();
		
		for(Livro livro : livros) {
			LivroDTO livroDTO = new LivroDTO();
			
			livroDTO.setId(livro.getId());
			livroDTO.setTitulo(livro.getTitulo());
			livroDTO.setDescricao(livro.getDescricao());
			livroDTO.setPaginas("" + livro.getPaginas());
			livroDTO.setDataLancamento(dataLiquida(livro.getDataLancamento()));
			livroDTO.setPreco(moeda(livro.getPreco()));
			
			if(livro.getSumarioPath() == null) 
				livroDTO.setSumarioPath(generico);
			 else 
				livroDTO.setSumarioPath(livro.getSumarioPath());
			
			livrosDTO.add(livroDTO);
			
		}
		
		return livrosDTO;
	}
	
	private String dataLiquida(Calendar cal) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		String data = format.format(cal.getTime());
		
		return data;
	}
	
	private String moeda(double moeda) {
		return NumberFormat.getCurrencyInstance(new Locale("pt", "br")).format(moeda);
	}

}
