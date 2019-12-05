package br.com.livraria.livraria.controller;

//import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.livraria.livraria.conf.TokenService;
import br.com.livraria.livraria.model.dto.TokenDTO;
import br.com.livraria.livraria.model.form.LoginForm;


@Controller
public class LoginController {
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginForm() {
		return "login";
	}
	
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String adminloginForm() {
		return "login";
	}
	
	@RequestMapping(value="/entrar", method=RequestMethod.POST)
	public String entrar(@Valid LoginForm form, RedirectAttributes redirectAttributes) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authentication = manager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			ResponseEntity.ok(new TokenDTO(token, "Bearer"));
			
			return "redirect:/admin/lista";
		}catch(AuthenticationException e ) {
			ResponseEntity.badRequest().build();
			redirectAttributes.addFlashAttribute("msg_resultado", "Login e/ou senha incorretos!");
			return "redirect:/login";
		}
		
	}
	
	/*
	@RequestMapping(value="/sair", method=RequestMethod.GET)
	public String sair(HttpServletRequest request) {
		String auth = request.getHeader("Authorization");
		if(auth != null && auth.contains("Bearer")) {
			String tokenId = auth.substring("Bearer".length()+1);
			manager.remove
		}
		
		return "redirect:/";
	}
	*/
}
