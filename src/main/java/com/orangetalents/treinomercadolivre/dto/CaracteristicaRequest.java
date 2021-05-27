package com.orangetalents.treinomercadolivre.dto;

import javax.validation.constraints.NotBlank;

import com.orangetalents.treinomercadolivre.model.Caracteristica;

public class CaracteristicaRequest {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String valor;

	public CaracteristicaRequest(@NotBlank String nome, @NotBlank String valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public String getValor() {
		return valor;
	}
	
	public Caracteristica toModel() {
		return new Caracteristica(nome, valor);
	}
	
}
