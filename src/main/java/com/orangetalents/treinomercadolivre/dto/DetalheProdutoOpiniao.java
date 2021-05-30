package com.orangetalents.treinomercadolivre.dto;

import com.orangetalents.treinomercadolivre.model.OpiniaoProduto;

public class DetalheProdutoOpiniao {

	private String usuario;
	private Integer nota;
	private String titulo;
	private String descricao;

	public DetalheProdutoOpiniao(OpiniaoProduto opiniao) {
		this.usuario = opiniao.getUsuario().getLogin();
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
	}

	public String getUsuario() {
		return usuario;
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

}
