package com.cursospboot.repository;

import javax.transaction.Transactional;

import com.cursospboot.model.Pessoa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cursospboot.model.Telefone;

import java.util.List;

@Repository
@Transactional
public interface TelefoneRepository extends CrudRepository<Telefone, Long>{

    @Query("select t from Telefone t where t.pessoa = ?1")
	List<Telefone> findAllByIdPessoa(Pessoa pessoa);
}
