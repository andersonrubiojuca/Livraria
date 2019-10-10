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

import com.github.jscookie.javacookie.Cookies;
import com.github.jscookie.javacookie.Expiration;
import com.github.jscookie.javacookie.Cookies.Attributes;

//import br.com.livraria.livraria.model.CarrinhoCompras;
import br.com.livraria.livraria.model.CarrinhoLivros;
import br.com.livraria.livraria.model.dto.LivroDTO;
import br.com.livraria.livraria.model.Livro;
import br.com.livraria.livraria.model.LivroQuant;
import br.com.livraria.livraria.service.LivroService;
import br.com.livraria.livraria.utils.ConversorDeJSONeObj;

@Controller
public class ProdutoController {
	
	@Autowired
	private LivroService service;
	
	private ConversorDeJSONeObj conversor = new ConversorDeJSONeObj();
	/*
	@Autowired
	private CarrinhoCompras carrinho;
	*/

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
			RedirectAttributes redirectAttributes,
			HttpServletRequest request, 
			HttpServletResponse response
			) {
		
		Optional<Livro> livroOp = service.listarPorId(Long.parseLong(livroId));
		Cookies cookies = Cookies.initFromServlet( request, response );
		
		CarrinhoLivros carrinhol;
				
		String carrinhoc = cookies.get("carrinho");
		
		
		if(carrinhoc != null) {
			carrinhol = conversor.toCarrinhoLivros(carrinhoc);
		} else {
			carrinhol = new CarrinhoLivros();
		}
		
		if(livroOp.isPresent()) {
			LivroQuant livroq = new LivroQuant(livroOp.get());
			
			carrinhol.add(livroq);
			
			System.out.println(conversor.toJSON(carrinhol));
			
			cookies.set("carrinho", conversor.toJSON(carrinhol), Attributes.empty()
					.expires(Expiration.days(1)));
			
			model.addAttribute("carrinho", carrinhol);
			
			return "redirect:/carrinho";
			
		} else {
			return "erro/404";
		}
		
	}
	
	/*
	 * @RequestMapping(value="/carrinho", method=RequestMethod.POST)
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
	 */
	
	@RequestMapping(value="/carrinho", method=RequestMethod.GET)
	public String lista(Model model,
			HttpServletRequest request, 
			HttpServletResponse response) {
		Cookies cookies = Cookies.initFromServlet( request, response );
		String carrinhoc = cookies.get("carrinho");
		
		CarrinhoLivros carrinhol = conversor.toCarrinhoLivros(carrinhoc);
		
		model.addAttribute("carrinho", carrinhol);
		
		return "cliente/itens";
	}
	
	@SuppressWarnings("unused")
	private void biscoito(HttpServletRequest request, 
			HttpServletResponse response) {
		Cookies cookies = Cookies.initFromServlet( request, response );
		
		String carrinho = cookies.get("carrinho");
		if(carrinho != null) {
			
		}
	}
	
}
