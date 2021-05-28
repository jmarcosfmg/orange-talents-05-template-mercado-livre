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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.treinomercadolivre.dto.ImagemRequest;
import com.orangetalents.treinomercadolivre.model.ImagemProduto;
import com.orangetalents.treinomercadolivre.model.Produto;
import com.orangetalents.treinomercadolivre.model.Usuario;

@RestController
@RequestMapping("/produto")
public class ImagemProdutoController {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ImageSender imageSender;

	@PostMapping
	@RequestMapping("/{id}/imagens")
	@Transactional
	public ResponseEntity<?> insereImagem(@AuthenticationPrincipal Optional<Usuario> usuario,
			@PathVariable("id") Long produtoId, @Valid ImagemRequest request) {
		Produto produto = em.find(Produto.class, produtoId);
		Assert.isTrue(produto != null, "O produto não existe");
		if (usuario.isEmpty() || produto.getIdDono() != usuario.get().getId())
			return ResponseEntity.status(403).build();
		Set<ImagemProduto> imagens = new HashSet<>();
		request.getFile().forEach(file -> {
			String link = imageSender.sendImage(produtoId, file);
			imagens.add(new ImagemProduto(produto, link));
		});
		imagens.forEach(im -> em.persist(im));
		return ResponseEntity.ok().build();
	}
}
