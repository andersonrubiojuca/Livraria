package br.com.livraria.livraria.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.livraria.livraria.model.CarrinhoLivros;
import br.com.livraria.livraria.model.dto.LivroDTO;
import br.com.livraria.livraria.model.Livro;
import br.com.livraria.livraria.service.LivroService;

@Controller
public class ProdutoController {
	
	@Autowired
	private LivroService service;
	
	
	@Autowired
	private CarrinhoLivros carrinho;
	
	

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
			LivroDTO livro = new LivroDTO(livroOp.get());
			
			carrinho.add(livro);
			
			
			model.addAttribute("carrinho", carrinho);
			
			
			return "redirect:/carrinho";
			
		} else {
			return "erro/404";
		}
		
	}
	
	
	@RequestMapping(value="/carrinho", method=RequestMethod.GET)
	public String lista(Model model,
			HttpServletRequest request, 
			HttpServletResponse response) {
		
		model.addAttribute("carrinho", carrinho);
		
		return "cliente/itens";
	}
	
	@RequestMapping(value="/limpar")
	public String limpar(Model model) {
		
		carrinho.limpa();
		model.addAttribute("carrinho", carrinho);
		
		return "redirect:/carrinho";
	}
	
	@RequestMapping(value="/finalizar")
	public String finalizar(Model model) {
		
		model.addAttribute("carrinho", carrinho);
		
		return "cliente/finalizar";
	}
	
	@RequestMapping(value="/remover/{id}")
	public String remover(Model model,
			@PathVariable("id") String id) {
		
		carrinho.remover(Long.parseLong(id));
		
		model.addAttribute("carrinho", carrinho);
		
		return "redirect:/carrinho";
	}
}
