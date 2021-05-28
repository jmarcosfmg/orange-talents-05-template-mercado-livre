package com.orangetalents.treinomercadolivre.configuration;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.orangetalents.treinomercadolivre.model.Usuario;
import com.orangetalents.treinomercadolivre.repository.UsuarioRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class TokenFilter extends OncePerRequestFilter {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Value("${mercado-livre.jwt.secret}")
	private String secret;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");
		if (token != null && !token.isBlank()) {
			if (token.startsWith("Bearer ")) {
				token = token.substring(6, token.length()).strip();
			}
			Claims cl = (Claims) Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
			String login = cl.getSubject();
			Optional<Usuario> user = usuarioRepository.findByLoginIgnoreCase(login);
			if (user.isPresent()) {
				Hibernate.initialize(user.get().getAuthorities());
				UsernamePasswordAuthenticationToken au = new UsernamePasswordAuthenticationToken(user, null,
						user.get().getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(au);
			}
		}

		filterChain.doFilter(request, response);
	}

}
