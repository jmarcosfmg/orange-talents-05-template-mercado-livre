package com.orangetalents.treinomercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.treinomercadolivre.dto.ProdutoRequest;
import com.orangetalents.treinomercadolivre.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@PostMapping
	public ResponseEntity<?> criaProduto(@RequestBody @Valid ProdutoRequest produtoRequest) {
		produtoRepository.save(produtoRequest.toModel());
		return ResponseEntity.ok().build();
	}
}
