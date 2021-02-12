package com.cursospboot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_telefone;
	
	private String numero;
	private String tipo;
	
	@ManyToOne
	private Pessoa pessoa;

	private Date data_cadastro;

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Long getId_telefone() {
		return id_telefone;
	}

	public void setId_telefone(Long id_telefone) {
		this.id_telefone = id_telefone;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Telefone [id_telefone=" + id_telefone + ", numero=" + numero + ", tipo=" + tipo + ", pessoa=" + pessoa + "]";
	}
	
}
