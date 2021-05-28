package com.orangetalents.treinomercadolivre.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class OpiniaoProduto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	private Produto produto;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@Min(1)
	@Max(5)
	private Integer nota;
	
	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max=500)
	private String descricao;

	public OpiniaoProduto(@NotNull Produto produto, @NotNull Usuario usuario,
			@NotNull @Size(min = 1, max = 5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		this.produto = produto;
		this.usuario = usuario;
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}
	
	@Deprecated
	public OpiniaoProduto() {
	}

	public Long getId() {
		return id;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getUsuario() {
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
