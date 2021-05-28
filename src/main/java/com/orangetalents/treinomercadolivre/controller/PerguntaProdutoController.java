package com.orangetalents.treinomercadolivre.controller;

import java.util.Optional;

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

import com.orangetalents.treinomercadolivre.dto.PerguntaProdutoRequest;
import com.orangetalents.treinomercadolivre.model.PerguntaProduto;
import com.orangetalents.treinomercadolivre.model.Produto;
import com.orangetalents.treinomercadolivre.model.Usuario;

@RestController
@RequestMapping("/produto")
public class PerguntaProdutoController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private EmailSender es;
	
	@PostMapping
	@RequestMapping("/{id}/perguntas")
	@Transactional
	public ResponseEntity<?> inserePergunta(@AuthenticationPrincipal Optional<Usuario> usuario,@PathVariable("id") Long produtoId ,@RequestBody @Valid PerguntaProdutoRequest pergunta){
		Produto produto = em.find(Produto.class, produtoId);
		Assert.isTrue(produto != null, "O produto não existe");
		if(usuario.isEmpty()) return ResponseEntity.status(403).build();
		PerguntaProduto perguntaModel = pergunta.toModel(usuario.get(), produto);
		em.persist(perguntaModel);
		es.sendPergunta(usuario.get(), perguntaModel);
		return ResponseEntity.ok().build();
	}
}
