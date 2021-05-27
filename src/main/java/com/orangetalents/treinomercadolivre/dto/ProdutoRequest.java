package com.orangetalents.treinomercadolivre.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.orangetalents.treinomercadolivre.model.Caracteristica;
import com.orangetalents.treinomercadolivre.model.Categoria;
import com.orangetalents.treinomercadolivre.model.Produto;
import com.orangetalents.treinomercadolivre.validator.CampoVerificado;

public class ProdutoRequest {
	
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
	
	@NotNull
	@Size(min = 3)
	private List<CaracteristicaRequest> caracteristicas;

	@NotNull
	@CampoVerificado(atributo = "id", classe = Categoria.class)
	private Long categoria;

	public ProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidade, @NotBlank @Size(max = 1000) String descricao,
			@NotNull @Size(min = 3) List<CaracteristicaRequest> caracteristicas, @NotNull Long categoria) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.caracteristicas = caracteristicas;
		this.categoria = categoria;
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

	public List<CaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

	public Long getCategoria() {
		return categoria;
	}
	
	public Produto toModel() {
		Assert.isTrue(caracteristicas.size() >=3, "O produto deve possuir no mínimo 3 características");
		Assert.isTrue(categoria != null, "A categoria não pode ser nula");
		Assert.isTrue(!descricao.isBlank(), "Insira uma descrição");
		
		List<Caracteristica> caracteristicasModel = this.caracteristicas.stream().map(c -> 
		c.toModel()).collect(Collectors.toList());
		Produto produto = new Produto(nome, valor, quantidade, descricao, caracteristicasModel, 
						new Categoria(this.categoria));
		caracteristicasModel.forEach(c -> c.setProduto(produto));
		return produto;
	}

}
