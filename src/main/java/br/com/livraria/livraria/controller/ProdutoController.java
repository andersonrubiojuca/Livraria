package br.com.livraria.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
