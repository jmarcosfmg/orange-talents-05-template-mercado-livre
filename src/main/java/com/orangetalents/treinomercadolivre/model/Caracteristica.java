package com.orangetalents.treinomercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Caracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Produto produto;

	@NotBlank
	private String nome;

	@NotBlank
	private String valor;

	public Caracteristica(@NotBlank String nome, @NotBlank String valor) {
		this.nome = nome;
		this.valor = valor;
	}
	
	@Deprecated
	public Caracteristica() {
	}

	public void setProduto(Produto p) {
		this.produto = p;
	}

	public String getNome() {
		return nome;
	}

	public String getValor() {
		return valor;
	}
}
