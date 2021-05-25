package com.orangetalents.treinomercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.treinomercadolivre.dto.UsuarioRequest;

@RestController
@RequestMapping("/cliente")
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criaCliente(@RequestBody @Valid UsuarioRequest clienteRequest){
		try {
			em.persist(clienteRequest.toModel());
			return ResponseEntity.ok().build();
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
