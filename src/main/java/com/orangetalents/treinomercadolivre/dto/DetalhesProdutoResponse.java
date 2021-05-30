package com.orangetalents.treinomercadolivre.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.OptionalDouble;

import com.orangetalents.treinomercadolivre.model.Produto;

public class DetalhesProdutoResponse {

	private String nome;

	private String descricao;

	private List<String> imagens;

	private BigDecimal preco;

	private List<DetalheProdutoCaracteristica> caracteristicas;

	private Double media;

	private Integer totalNotas;

	private List<DetalheProdutoOpiniao> opinioes;

	private List<String> perguntas;

	public DetalhesProdutoResponse(Produto produto) {
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.descricao = produto.getDescricao();
		this.caracteristicas = produto.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
		this.imagens = produto.mapeiaImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
		this.opinioes = produto.mapeiaOpinioes(DetalheProdutoOpiniao::new);
		this.totalNotas = produto.getOpinioes().size();
		OptionalDouble media = produto.buscaMedia();
		this.media = media.orElse(0.0);
	}

	public String getNome() {
		return nome;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public List<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Double getMedia() {
		return media;
	}

	public Integer getTotalNotas() {
		return totalNotas;
	}

	public List<DetalheProdutoOpiniao> getOpinioes() {
		return opinioes;
	}

	public List<String> getPerguntas() {
		return perguntas;
	}

	public String getDescricao() {
		return descricao;
	}
}
