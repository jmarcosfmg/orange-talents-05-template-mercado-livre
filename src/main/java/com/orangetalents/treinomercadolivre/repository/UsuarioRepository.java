package com.orangetalents.treinomercadolivre.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orangetalents.treinomercadolivre.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	public Optional<Usuario> findByLoginIgnoreCase(String login);

}
