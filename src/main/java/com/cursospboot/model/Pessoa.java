package com.cursospboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Nome não pode estar null")
	@NotEmpty(message = "Nome não pode estar vazio")
	private String nome;

	@NotNull(message = "Sobrenome não pode estar null")
	@NotEmpty(message = "Sobrenome não pode estar vazio")
	private String sobrenome;

	@NotNull(message = "Email não pode estar null")
	@NotEmpty(message = "Email não pode estar vazio")
	private String email;

	@NotNull(message = "Senha não pode estar null")
	@NotEmpty(message = "Senha não pode estar vazio")
	private String senha;

	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<Telefone> telefones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
