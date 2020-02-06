package br.com.livraria.livraria.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.livraria.livraria.conf.email.EnviarEmail;
import br.com.livraria.livraria.model.CarrinhoLivros;
import br.com.livraria.livraria.model.CompraEnvio;
import br.com.livraria.livraria.model.CompraReq;
import br.com.livraria.livraria.model.Compras;
import br.com.livraria.livraria.model.Livro;
import br.com.livraria.livraria.model.dto.LivroDTO;
import br.com.livraria.livraria.model.form.CompraForm;
import br.com.livraria.livraria.service.ComprasService;
import br.com.livraria.livraria.service.LivroService;
import br.com.livraria.livraria.utils.ConversorDeJSONeObj;

@Controller
@RequestMapping(value="site")
public class ProdutoController {
	
	@Autowired
	private LivroService service;
	
	@Autowired
	private ComprasService compraService;
	
	@Autowired
	private CarrinhoLivros carrinho;
	
	@Autowired
	private RestTemplate restTemplate;
	
	

	@RequestMapping(value="/detalhes/{id}", method=RequestMethod.GET)
	public String produto(Model model, @PathVariable("id") String idRaw) {
		Long id;
		try {
			id = Long.parseLong(idRaw);
		} catch(NumberFormatException e) {
			return "redirect:../../error/404";
		}
		
		Optional<Livro> livroOp = service.listarPorId(id);
		
		if(livroOp.isPresent()) {
			LivroDTO livroDto = new LivroDTO(livroOp.get());
			
			model.addAttribute("livro", livroDto);
			
			return "cliente/detalhes";
		} else {
			return "redirect:../../error/404";
		}
		
	}
	
	
	@RequestMapping(value="/carrinho", method=RequestMethod.POST)
	public String itens(
			@RequestParam("livroId") String livroId,
			Model model,
			RedirectAttributes redirectAttributes
			) {
		
		Long id;
		try {
			id = Long.parseLong(livroId);
		} catch(NumberFormatException e) {
			return "redirect:../../error/404";
		}
		
		Optional<Livro> livroOp = service.listarPorId(id);
		
		
		if(livroOp.isPresent()) {
			LivroDTO livro = new LivroDTO(livroOp.get());
			
			carrinho.add(livro);
			
			
			model.addAttribute("carrinho", carrinho);
			
			
			return "redirect:/site/carrinho";
			
		} else {
			return "redirect:../../error/404";
		}
		
	}
	
	
	@RequestMapping(value="/carrinho", method=RequestMethod.GET)
	public String lista(Model model) {
		
		model.addAttribute("carrinho", carrinho);
		
		return "cliente/itens";
	}
	
	@RequestMapping(value="/limpar")
	public String limpar(Model model) {
		
		carrinho.limpa();
		model.addAttribute("carrinho", carrinho);
		
		return "redirect:/site/carrinho";
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
		
		return "redirect:/site/carrinho";
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
		
		if(requisicao(compraEnvio)) {
			
			Compras compras = compraEnvio.getCompra();
			compraService.salvar(compras);
			
			
			enviaEmail(compraEnvio);
			
			carrinho.limpa();
			redirectAttributes.addFlashAttribute("msg_resultado", "Compra feita! "
					+ "Aguarde o email.");
			
			return "redirect:/";
		} else {
			return "cliente/finalizar";
		}
		
	}


	private boolean requisicao(CompraEnvio compraEnvio) {
		final String uri = "http://httpbin.org/post";
		ConversorDeJSONeObj conversor = new ConversorDeJSONeObj();
		
		CompraReq compraReq = new CompraReq(compraEnvio);
		String json = conversor.toJSON(compraReq);
		try {
			@SuppressWarnings("unused")
			String response = restTemplate.postForObject(uri, json, String.class);
			return true;
		} catch(HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
			return false;
		}
	}
	
	private void enviaEmail(CompraEnvio compraEnvio) {
		EnviarEmail enviar = new EnviarEmail();
		
		enviar.enviar(compraEnvio);
	}
	
	private Date getAgora() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date(cal.getTimeInMillis());
		
		return date;
	}
}
