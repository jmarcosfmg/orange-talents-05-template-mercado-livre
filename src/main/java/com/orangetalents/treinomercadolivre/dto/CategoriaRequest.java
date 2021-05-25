package com.orangetalents.treinomercadolivre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import com.orangetalents.treinomercadolivre.model.Categoria;
import com.orangetalents.treinomercadolivre.validator.Unico;

public class CategoriaRequest {
	
	@NotBlank
	@Unico(atributo = "nome", classe = Categoria.class)
	private String nome;

	private Long categoriaMae;

	public CategoriaRequest(@NotBlank String nome, Long categoriaMae) {
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}

	public Long getCategoriaMae() {
		return categoriaMae;
	}

	public String getNome() {
		return nome;
	}
	
	public Categoria toModel(EntityManager em) {
		Categoria novaCategoria = new Categoria(this.nome);
		if(this.categoriaMae != null) {
			Categoria mae = em.find(Categoria.class, this.categoriaMae);
			Assert.isTrue(mae != null, "Categoria mãe não encontrada");
			novaCategoria.setCategoriaMae(mae);
		}
		return novaCategoria;
	}
	
	
}
