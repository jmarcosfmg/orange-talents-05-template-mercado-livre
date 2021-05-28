package com.orangetalents.treinomercadolivre.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orangetalents.treinomercadolivre.dto.LoginRequest;
import com.orangetalents.treinomercadolivre.model.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${mercado-livre.jwt.secret}")
	private String secret;

	@Value("${mercado-livre.jwt.expiration-time}")
	private Long expiration;

	@PostMapping
	@RequestMapping("/login")
	public ResponseEntity<?> criaLogin(@RequestBody @Valid LoginRequest loginRequest) {
		try {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		Usuario user = (Usuario) auth.getPrincipal();
		Date instante = new Date();
		Date expiracao = new Date(instante.getTime() + expiration);
		return ResponseEntity.ok()
				.body(Jwts.builder().setIssuer("mercadolivreapplication").setSubject(user.getUsername())
						.setIssuedAt(instante).setExpiration(expiracao).signWith(SignatureAlgorithm.HS512, this.secret)
						.compact());
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body(e);
		}
	}
}
