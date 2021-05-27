package com.orangetalents.treinomercadolivre.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {
	
	@NotBlank
	private String username;
	
	@NotBlank
	@Size(min = 6)
	private String password;

	public LoginRequest(String login, String password) {
		this.username = login;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
}
