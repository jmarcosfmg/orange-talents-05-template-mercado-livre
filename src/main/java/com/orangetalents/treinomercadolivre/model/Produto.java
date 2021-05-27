package com.orangetalents.treinomercadolivre.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	@NotBlank
	@Size(max = 1000)
	private String descricao;

	private LocalDateTime instanteDeCadastro;
	@NotNull
	@Size(min = 3)
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "produto", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Caracteristica> caracteristicas;

	@ManyToOne
	private Categoria categoria;

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidade, @NotBlank @Size(max = 1000) String descricao,
			@NotNull @Size(min = 3) List<Caracteristica> caracteristicas, Categoria categoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.instanteDeCadastro = LocalDateTime.now();
		this.caracteristicas = caracteristicas;
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getInstanteDeCadastro() {
		return instanteDeCadastro;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Categoria getCategoria() {
		return categoria;
	}
}
