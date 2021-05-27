package com.orangetalents.treinomercadolivre.dto;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import com.orangetalents.treinomercadolivre.model.Categoria;
import com.orangetalents.treinomercadolivre.repository.CategoriaRepository;
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

	public Categoria toModel(CategoriaRepository categoriaRepository) {
		Categoria novaCategoria = new Categoria(this.nome);
		if (this.categoriaMae != null) {
			Optional<Categoria> mae = categoriaRepository.findById(this.categoriaMae);
			Assert.isTrue(mae.isPresent(), "Categoria mãe não encontrada");
			novaCategoria.setCategoriaMae(mae.get());
		}
		return novaCategoria;
	}

}
