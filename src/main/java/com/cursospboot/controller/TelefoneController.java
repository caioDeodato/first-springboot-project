package com.cursospboot.controller;

import com.cursospboot.model.Pessoa;
import com.cursospboot.model.Telefone;
import com.cursospboot.repository.PessoaRepository;
import com.cursospboot.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class TelefoneController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @GetMapping("**/telefones/{idpessoa}")
    public ModelAndView telefones(@PathVariable("idpessoa") Long id) {

        ModelAndView view = new ModelAndView("cadastro/telefones");
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        List<Telefone> telefones = telefoneRepository.findAllByIdPessoa(pessoa.get());

        view.addObject("telefones", telefones);

        view.addObject("pessoaobj", pessoa.orElse(null));

        return view;
    }

    @PostMapping("**/salvarTelefone/{pessoaid}")
    public ModelAndView cadastrar(@PathVariable("pessoaid") Long pessoaid, Telefone telefone) throws Exception{
        
    	ModelAndView modelAndView = new ModelAndView("cadastro/telefones");
        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaid);
        
        if((telefone.getNumero() != null && telefone.getNumero().isEmpty()) 
        		|| (telefone.getTipo() != null && telefone.getTipo().isEmpty())) {
        	
        	List<String> mensagens = new ArrayList<>();
        	
        	if(telefone.getNumero().isEmpty()) {
        		mensagens.add("Campo \"número\" não pode estar vazio");
        	}
        	
        	if(telefone.getTipo().isEmpty()) {
        		mensagens.add("Campo \"tipo\" não pode estar vazio");
        	}
        	
        	modelAndView.addObject("msgs", mensagens);
        	
        } else {
        	
        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataString = dateFormat.format(new Date());

            telefone.setData_cadastro( dateFormat.parse(dataString));
            telefone.setPessoa(pessoa.get());
            pessoa.get().setId(pessoaid);

            telefoneRepository.save(telefone);
        }
        
        modelAndView.addObject("pessoaobj", pessoa.get());
        modelAndView.addObject("telefones", telefoneRepository.findAllByIdPessoa(pessoa.get()));
        return modelAndView;
    }

    @GetMapping("/removerTelefone/{idfone}")
    public ModelAndView excluirTelefone(@PathVariable Long idfone) {
        ModelAndView view = new ModelAndView("cadastro/telefones");

        // pegar o telefone do banco pra retornar a pessoa e assim retornar para a page
        Pessoa pessoa = telefoneRepository.findById(idfone).get().getPessoa();

        telefoneRepository.deleteById(idfone);

        view.addObject("pessoaobj", pessoa);
        view.addObject("telefones", telefoneRepository.findAllByIdPessoa(pessoa));

        return view;
    }

}
