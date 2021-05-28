package com.orangetalents.treinomercadolivre.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.treinomercadolivre.dto.ImagemRequest;
import com.orangetalents.treinomercadolivre.dto.ProdutoRequest;
import com.orangetalents.treinomercadolivre.model.Categoria;
import com.orangetalents.treinomercadolivre.model.ImagemProduto;
import com.orangetalents.treinomercadolivre.model.Produto;
import com.orangetalents.treinomercadolivre.model.Usuario;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ImageUploader imageUploader;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criaProduto(@AuthenticationPrincipal Optional<Usuario> usuario, @RequestBody @Valid ProdutoRequest produtoRequest) {
		Assert.isTrue(produtoRequest.getCategoria() != null, "Categoria não pode ser nula");
		if(usuario.isEmpty()) return ResponseEntity.status(403).build();
		Categoria cat = em.find(Categoria.class, produtoRequest.getCategoria());
		em.persist(produtoRequest.toModel(cat, usuario.get()));
		return ResponseEntity.ok().build();
	}
	
	@PostMapping
	@RequestMapping("/{id}/imagens")
	@Transactional
	public ResponseEntity<?> insereImagem(@AuthenticationPrincipal Optional<Usuario> usuario,@PathVariable("id") Long produtoId ,@Valid ImagemRequest request){
		Produto produto = em.find(Produto.class, produtoId);
		if(usuario.isEmpty() || produto.getIdDono() != usuario.get().getId()) return ResponseEntity.status(403).build();
		Set<ImagemProduto> imagens = new HashSet<>();
		request.getFile().forEach(file -> {
			String link = imageUploader.sendImage(produtoId, file);
			imagens.add(new ImagemProduto(produto, link));
		});
		imagens.forEach(im -> em.persist(im));
		return ResponseEntity.ok().build();
	}
}
