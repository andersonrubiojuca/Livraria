package br.com.livraria.livraria.controller;



import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.livraria.livraria.infra.FileSaver;
import br.com.livraria.livraria.model.Livro;
import br.com.livraria.livraria.model.dto.LivroDTO;
import br.com.livraria.livraria.model.form.LivroForm;
import br.com.livraria.livraria.service.LivroService;

@Controller
@RequestMapping(value="admin")
public class LivroController {
	
	private LivroDTO dto = new LivroDTO(new Long(2));
	private final String generico = dto.getGenerico();
	
	@Autowired
	private LivroService service;
	
	
	@Autowired
    private FileSaver fileSaver;
	

	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String form(Model model) {

		model.addAttribute("livro", new Livro());
		
		return new String("admin/adicionar");
	}
	
	@RequestMapping(value="/lista", method=RequestMethod.POST)
	public ModelAndView salvar(
							@Valid LivroForm livroForm,
							final BindingResult result,
							@RequestParam("sumario") MultipartFile sumario,
							Model model,
							RedirectAttributes redirectAttributes) throws IOException{
		
		Livro livro = livroForm.getLivro();
		
		
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("msg_resultado", "Erro em alguns campos!");
			System.out.println(livro);
			
			ModelAndView modelAndView = new ModelAndView("redirect:/form");
			return modelAndView;
		}
		
		if(!sumario.isEmpty()) {
			String path = fileSaver.write(sumario);
			livro.setSumarioPath(path);
		} else {
			livro.setSumarioPath(generico);
		}
		
		service.salvar(livro);
		
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/lista");
		
		redirectAttributes.addFlashAttribute("msg_resultado", "Livro salvo com sucesso!");
		return modelAndView;
	}
	
	@RequestMapping(value="/lista", method=RequestMethod.GET)
	public String lista(Model model) {
		model.addAttribute("livros", service.listar());
		
		return "admin/lista";
	}
	
	@RequestMapping(value="/deletar/{id}", method=RequestMethod.GET)
	public String deletar(Model model, @PathVariable("id") Long id) {
		Optional<Livro> livroOp = service.listarPorId(id);
		
		if(livroOp.isPresent()) {
			Livro livro = livroOp.get();
			
			if(!livro.getSumarioPath().equals(generico)) {
				fileSaver.delete(livro.getSumarioPath());
			} 
			
			service.remover(id);
			
			return "redirect:../lista";
		} else {
			return "redirect:../../error";
		}
	}
	
	@RequestMapping(value="/altera/{id}", method=RequestMethod.GET)
	public String alterarPag(Model model, @PathVariable("id") Long id) {
		Optional<Livro> livroOp = service.listarPorId(id);
		
		if(livroOp.isPresent()) {
			Livro livro = livroOp.get();
			
			LivroDTO livroDTO = new LivroDTO(livro);
			livroDTO.dataInput(livro.getDataLancamento());
			livroDTO.precoForm();
			
			model.addAttribute("livro", livroDTO);
			
			return "admin/editar";
		} else {
			return "erro/404";
		}
	}
	
	@RequestMapping(value="/alterar", method=RequestMethod.POST)
	public String alterarPost(@Valid LivroForm livroForm,
							final BindingResult result,
							@RequestParam("sumario") MultipartFile sumario,
							@RequestParam("livroId") Long id,
							Model model,
							RedirectAttributes redirectAttributes) {
		Livro livro = livroForm.getLivro();
		livro.setId(id);
		
		Optional<Livro> livroOpRaw = service.listarPorId(id);
		
		if(result.hasErrors() || !livroOpRaw.isPresent()) {
			redirectAttributes.addFlashAttribute("msg_resultado", "Erro em alguns campos!");
			
			return "redirect:../admin/lista";
		}
		
		Livro livroRaw = livroOpRaw.get();
		
		if(sumario.isEmpty()) {
			livro.setSumarioPath(livroRaw.getSumarioPath());
		} else {
			
			if(!livroRaw.getSumarioPath().equals(generico)) {
				fileSaver.delete(livroRaw.getSumarioPath());
			} 
			
			String path = fileSaver.write(sumario);
			livro.setSumarioPath(path);
		}
		
		service.salvar(livro);
		
		redirectAttributes.addFlashAttribute("msg_resultado", "Livro editado com sucesso!");
		return "redirect:../admin/lista";
	}
	
	
}
