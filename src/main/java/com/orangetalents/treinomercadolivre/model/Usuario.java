package com.orangetalents.treinomercadolivre.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime instante = LocalDateTime.now();

	@Email
	@NotBlank
	@Column(unique=true)
	private String login;

	@NotBlank
	private String senha;

	public Usuario(@Email @NotBlank String login, @NotBlank String senha) {
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

}
