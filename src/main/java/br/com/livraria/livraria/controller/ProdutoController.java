package br.com.livraria.livraria.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.livraria.livraria.model.CarrinhoCompras;
import br.com.livraria.livraria.model.dto.LivroDTO;
import br.com.livraria.livraria.model.Livro;
import br.com.livraria.livraria.model.LivroQuant;
import br.com.livraria.livraria.service.LivroService;

@Controller
public class ProdutoController {
	
	@Autowired
	private LivroService service;
	
	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping(value="/detalhes/{id}", method=RequestMethod.GET)
	public String produto(Model model, @PathVariable("id") Long id) {
		Optional<Livro> livroOp = service.listarPorId(id);
		
		if(livroOp.isPresent()) {
			LivroDTO livroDto = new LivroDTO(livroOp.get());
			
			model.addAttribute("livro", livroDto);
			
			return "cliente/detalhes";
		} else {
			return "erro/404";
		}
		
	}
	
	@RequestMapping(value="/carrinho", method=RequestMethod.POST)
	public String itens(
			@RequestParam("livroId") String livroId,
			Model model,
			RedirectAttributes redirectAttributes
			) {
		
		Optional<Livro> livroOp = service.listarPorId(Long.parseLong(livroId));
		
		if(livroOp.isPresent()) {
			LivroQuant livroq = new LivroQuant(livroOp.get());
			
			carrinho.add(livroq);
			
			model.addAttribute("carrinho", carrinho);
			
			return "redirect:/carrinho";
			
		} else {
			return "erro/404";
		}
		
	}
	
	@RequestMapping(value="/carrinho", method=RequestMethod.GET)
	public String lista(Model model) {
		model.addAttribute("carrinho", carrinho);
		
		return "cliente/itens";
	}
	
}
