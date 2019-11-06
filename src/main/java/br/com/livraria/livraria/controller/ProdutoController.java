package br.com.livraria.livraria.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.livraria.livraria.conf.EnviarEmail;
import br.com.livraria.livraria.model.CarrinhoLivros;
import br.com.livraria.livraria.model.CompraEnvio;
import br.com.livraria.livraria.model.Compras;
import br.com.livraria.livraria.model.dto.LivroDTO;
import br.com.livraria.livraria.model.form.CompraForm;
import br.com.livraria.livraria.model.Livro;
import br.com.livraria.livraria.service.ComprasService;
import br.com.livraria.livraria.service.LivroService;

@Controller
public class ProdutoController {
	
	@Autowired
	private LivroService service;
	
	@Autowired
	private ComprasService compraService;
	
	
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
		
		if(carrinho.getItens().isEmpty()) {
			return "redirect:/";
		} else {
			
			model.addAttribute("carrinho", carrinho);
			
			return "cliente/finalizar";
		}
		
	}
	
	@RequestMapping(value="/remover/{id}")
	public String remover(Model model,
			@PathVariable("id") String id) {
		
		carrinho.remover(Integer.parseInt(id));
		
		model.addAttribute("carrinho", carrinho);
		
		return "redirect:/carrinho";
	}
	
	@RequestMapping(value="/terminado", method=RequestMethod.POST)
	public String terminado(@Valid CompraForm compraForm,
							final BindingResult result,
							RedirectAttributes redirectAttributes,
							Model model) {
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("msg_resultado", "Erro em alguns campos!");
			System.out.println(compraForm);
			
			return "cliente/finalizar";
		}
		
		CompraEnvio compraEnvio = compraForm.getCompra();
		carrinho.setDate(getAgora());
		compraEnvio.setCarrinho(carrinho);
		
		Compras compras = compraEnvio.getCompra();
		compraService.salvar(compras);
		
		EnviarEmail enviar = new EnviarEmail();
		
		try {
			enviar.enviar(compraEnvio);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		carrinho.limpa();
		redirectAttributes.addFlashAttribute("msg_resultado", "Compra feita! "
				+ "Aguarde o email e o prazo de entrega.");
		
		return "redirect:/";
	}
	
	private Date getAgora() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date(cal.getTimeInMillis());
				
		System.out.println(date);
		
		return date;
	}
}
