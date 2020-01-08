package br.com.livraria.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.livraria.livraria.service.LivroService;

@Controller
public class IndexController {
	
	@Autowired
	private LivroService service;
	
	@RequestMapping("/")
    public String index(Model model){
		
		model.addAttribute("livros", service.listar());
		
        return "index";
    }
	
	@RequestMapping("cliente/sobre")
	public String sobre() {
		return "cliente/sobre";
	}

}
