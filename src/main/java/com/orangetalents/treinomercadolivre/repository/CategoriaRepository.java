package com.orangetalents.treinomercadolivre.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orangetalents.treinomercadolivre.model.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long>{

	public Optional<Categoria> findById(Long categoriaMae); 

}
