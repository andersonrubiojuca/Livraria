package br.com.livraria.livraria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.livraria.livraria.model.Usuario;
import br.com.livraria.livraria.service.UsuarioService;

@Controller
public class LoginController {
	
	@Autowired
	private UsuarioService service;

	@RequestMapping(value="/login", method= {RequestMethod.GET, RequestMethod.POST})
	public String login() {
		return "/login";
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView view = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Usuario usuario = service.login(auth.getName()).get();
		
		view.addObject("nome", "Bem Vindo " + usuario.getLogin());
		
		view.setViewName("/admin/home");
		return view;
	}
}
