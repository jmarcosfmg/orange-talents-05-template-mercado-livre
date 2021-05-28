package com.orangetalents.treinomercadolivre.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private LocalDateTime instante;

	@Email
	@NotBlank
	@Column(unique = true)
	private String login;

	@NotBlank
	private String senha;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<TipoPerfil> perfil = new ArrayList<>();
	
	public Usuario(@Email @NotBlank String login, @NotBlank String senha) {
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
		this.instante = LocalDateTime.now();
	}

	@Deprecated
	public Usuario() {

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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfil;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
