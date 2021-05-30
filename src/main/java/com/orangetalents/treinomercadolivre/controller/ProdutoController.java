package com.orangetalents.treinomercadolivre.controller;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.treinomercadolivre.dto.DetalhesProdutoResponse;
import com.orangetalents.treinomercadolivre.dto.OpiniaoProdutoRequest;
import com.orangetalents.treinomercadolivre.dto.ProdutoRequest;
import com.orangetalents.treinomercadolivre.model.Categoria;
import com.orangetalents.treinomercadolivre.model.Produto;
import com.orangetalents.treinomercadolivre.model.Usuario;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@PersistenceContext
	private EntityManager em;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criaProduto(@AuthenticationPrincipal Optional<Usuario> usuario,
			@RequestBody @Valid ProdutoRequest produtoRequest) {
		Assert.isTrue(produtoRequest.getCategoria() != null, "Categoria não pode ser nula");
		if (usuario.isEmpty())
			return ResponseEntity.status(403).build();
		Categoria cat = em.find(Categoria.class, produtoRequest.getCategoria());
		em.persist(produtoRequest.toModel(cat, usuario.get()));
		return ResponseEntity.ok().build();
	}

	@PostMapping
	@RequestMapping("/{id}/opinioes")
	@Transactional
	public ResponseEntity<?> insereOpiniao(@AuthenticationPrincipal Optional<Usuario> usuario,
			@PathVariable("id") Long produtoId, @RequestBody @Valid OpiniaoProdutoRequest opiniao) {
		Produto produto = em.find(Produto.class, produtoId);
		Assert.isTrue(produto != null, "O produto não existe");
		if (usuario.isEmpty())
			return ResponseEntity.status(403).build();
		em.persist(opiniao.toModel(usuario.get(), produto));
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	@RequestMapping("/{id}/detalhes")
	@Transactional
	public ResponseEntity<DetalhesProdutoResponse> buscaDetalhes(@PathVariable("id") Long produtoId) {
		Produto produto = em.find(Produto.class, produtoId);
		Assert.isTrue(produto != null, "O produto não existe");
		DetalhesProdutoResponse detalhes = new DetalhesProdutoResponse(produto);
		return ResponseEntity.ok().body(detalhes);
	}
}
