package br.com.livraria.livraria.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.livraria.livraria.model.Livro;
import br.com.livraria.livraria.model.dto.LivroDTO;
import br.com.livraria.livraria.service.LivroService;

@Controller
public class ProdutoController {
	
	@Autowired
	private LivroService service;

	@RequestMapping(value="/detalhes/{id}", method=RequestMethod.GET)
	public String produto(Model model, @PathVariable("id") Long id) {
		
		model.addAttribute("livro", service.listarPorId(id));
		
		return "cliente/detalhes";
	}
	
	//lembrar de mecher nisso
	@RequestMapping(value="/Lista-de-itens")
	public String itens(
			@RequestParam("livroId") String livroId,
			final BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes
			) {
		Optional<Livro> livroOp = service.listarPorId(Long.getLong(livroId));
		
		//mecher nisso
		@Deprecated
		LivroDTO livro = new LivroDTO(livroOp.get());
		
		if(result.hasErrors()) {
			return "redirect:/index";
		}
		
		model.addAttribute("livro", livro);
		
		return "redirect:/cliente/itens";
	}
	
}
