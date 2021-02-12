package com.cursospboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cursospboot.model.Pessoa;
import com.cursospboot.repository.PessoaRepository;

import javax.validation.Valid;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/cadastroPessoa")
	public ModelAndView inicio() {
		ModelAndView view = new ModelAndView("cadastro/cadastroPessoa");
		view.addObject("pessoaobj", new Pessoa());
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "**/salvarPessoa")
	public ModelAndView salvarPessoa(@Valid Pessoa pessoa, BindingResult bindingResult) {
		
		ModelAndView view = new ModelAndView("cadastro/cadastroPessoa");

		if(bindingResult.hasErrors()) {
			Iterable<Pessoa> pessoas = pessoaRepository.findAllOrderById();
			view.addObject("pessoas", pessoas);
			view.addObject("pessoaobj", pessoa);

			List<String> msgs = new ArrayList<>();

			for (ObjectError objectError : bindingResult.getAllErrors()) {
				msgs.add(objectError.getDefaultMessage());
			}

			view.addObject("msgs", msgs);
			return view;
		}

		pessoaRepository.save(pessoa);

		Iterable<Pessoa> pessoas = pessoaRepository.findAllOrderById();
		view.addObject("pessoas", pessoas);
		view.addObject("pessoaobj", new Pessoa());

		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "**/listarTodos")
	public ModelAndView pessoas() {
		
		ModelAndView view = new ModelAndView("cadastro/cadastroPessoa");
		
		Iterable<Pessoa> pessoas = pessoaRepository.findAllOrderById();
		view.addObject("pessoas", pessoas);
		view.addObject("pessoaobj", new Pessoa());
		return view;
		
	}
	
	@GetMapping("/editarPessoa/{idpessoa}")
	public ModelAndView editarPessoa(@PathVariable("idpessoa") Long id) {
		
		ModelAndView view = new ModelAndView("cadastro/cadastroPessoa");
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		
		view.addObject("pessoaobj", pessoa.orElse(null));
		
		return view;
	}
	
	@GetMapping("/removerPessoa/{idpessoa}")
	public ModelAndView excluirPessoa(@PathVariable("idpessoa") Long id) {
		
		pessoaRepository.deleteById(id);
		
		ModelAndView view = new ModelAndView("cadastro/cadastroPessoa");
		Iterable<Pessoa> pessoas = pessoaRepository.findAllOrderById();
		view.addObject("pessoas", pessoas);
		view.addObject("pessoaobj", new Pessoa());
		
		return view;
	}
	
	@GetMapping("**/search")
	public ModelAndView pesquisaPorNome(@RequestParam("nome") String nome) {

		ModelAndView view = new ModelAndView("cadastro/cadastroPessoa");

		List<Pessoa> pessoas = pessoaRepository.findByName(nome);

		view.addObject("pessoas", pessoas);
		view.addObject("pessoaobj", new Pessoa());
		return view;
	}

}
