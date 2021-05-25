package com.orangetalents.treinomercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.orangetalents.treinomercadolivre.model.Usuario;
import com.orangetalents.treinomercadolivre.validator.Unico;

public class UsuarioRequest {

	@Email
	@NotBlank
	@Unico(atributo = "login", classe = Usuario.class)
	private String login;

	@NotBlank
	@Size(min = 6)
	private String senha;

	public UsuarioRequest(@Email @NotBlank String login, @NotBlank @Size(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public Usuario toModel() {
		Assert.isTrue(!this.senha.isBlank(), "Senha não pode ser vazia");
		Assert.isTrue(!this.login.isBlank(), "Login não pode ser vazio");
		return new Usuario(this.login, this.senha);
	}

}
