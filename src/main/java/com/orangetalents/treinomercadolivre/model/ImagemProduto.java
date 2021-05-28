package com.orangetalents.treinomercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ImagemProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Produto produto;

	private String link;

	public ImagemProduto(Produto produto, String link) {
		this.produto = produto;
		this.link = link;
	}

	@Deprecated
	public ImagemProduto() {
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public String getLink() {
		return link;
	}
}
