package com.orangetalents.treinomercadolivre.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.orangetalents.treinomercadolivre.model.OpiniaoProduto;
import com.orangetalents.treinomercadolivre.model.Produto;
import com.orangetalents.treinomercadolivre.model.Usuario;

public class OpiniaoProdutoRequest {

	@NotNull
	@Min(1)
	@Max(5)
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	public OpiniaoProdutoRequest(@NotNull @Size(min = 1, max = 5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public OpiniaoProduto toModel(Usuario usuario, Produto produto) {
		return new OpiniaoProduto(produto, usuario, this.nota, this.titulo, this.descricao);
	}
}
