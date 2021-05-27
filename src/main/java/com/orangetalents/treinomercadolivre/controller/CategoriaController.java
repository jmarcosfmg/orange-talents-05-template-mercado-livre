package com.orangetalents.treinomercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.treinomercadolivre.dto.CategoriaRequest;
import com.orangetalents.treinomercadolivre.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping
	public ResponseEntity<?> criaCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest) {
		categoriaRepository.save(categoriaRequest.toModel(categoriaRepository));
		return ResponseEntity.ok().build();
	}

}
