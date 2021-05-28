package com.orangetalents.treinomercadolivre.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

public enum TipoPerfil implements GrantedAuthority{
	
	USER,
	ADMIN;

	@Override
	public String getAuthority() {
		return "ROLE_"+this.name();
	}
	
}
