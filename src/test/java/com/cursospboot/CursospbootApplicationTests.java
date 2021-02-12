package com.cursospboot;

import com.cursospboot.model.Pessoa;
import com.cursospboot.model.Telefone;
import com.cursospboot.repository.PessoaRepository;
import com.cursospboot.repository.TelefoneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
class CursospbootApplicationTests {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Test
	public void cadastroTelefone() {

		Optional<Pessoa> pessoa = pessoaRepository.findById(2L);

		Telefone telefone = new Telefone();
		telefone.setNumero("129999-6666");
		telefone.setTipo("Móvel");
		telefone.setPessoa(pessoa.get());
		telefone.setData_cadastro(new Date());

		telefoneRepository.save(telefone);
	}

}
