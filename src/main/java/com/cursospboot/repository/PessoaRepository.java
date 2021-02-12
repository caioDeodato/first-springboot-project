package com.cursospboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cursospboot.model.Pessoa;

@Repository
@Transactional
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{

	@Query(value = "select p from Pessoa p ORDER BY p.id")
	public Iterable<Pessoa> findAllOrderById();
	
	@Query(value = "select p from Pessoa p where p.nome like %?1%")
	public List<Pessoa> findByName(String nome);
}
