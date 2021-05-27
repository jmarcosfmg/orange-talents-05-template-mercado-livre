package com.orangetalents.treinomercadolivre.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.treinomercadolivre.dto.UsuarioRequest;
import com.orangetalents.treinomercadolivre.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	public ResponseEntity<?> criaCliente(@RequestBody @Valid UsuarioRequest usuarioRequest) {
		usuarioRepository.save(usuarioRequest.toModel());
		return ResponseEntity.ok().build();
	}
}
