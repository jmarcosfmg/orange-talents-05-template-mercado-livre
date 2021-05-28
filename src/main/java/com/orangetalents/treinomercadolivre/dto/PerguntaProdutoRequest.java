package com.orangetalents.treinomercadolivre.dto;

import javax.validation.constraints.NotBlank;

import com.orangetalents.treinomercadolivre.model.PerguntaProduto;
import com.orangetalents.treinomercadolivre.model.Produto;
import com.orangetalents.treinomercadolivre.model.Usuario;

public class PerguntaProdutoRequest {

	@NotBlank
	private String titulo;

	public PerguntaProdutoRequest(@NotBlank String titulo) {
		this.titulo = titulo;
	}
	
	@Deprecated
	public PerguntaProdutoRequest() {
	}

	public String getTitulo() {
		return titulo;
	}
	
	public PerguntaProduto toModel(Usuario usuario, Produto produto) {
		return new PerguntaProduto(this.titulo, usuario, produto);
	}
}
