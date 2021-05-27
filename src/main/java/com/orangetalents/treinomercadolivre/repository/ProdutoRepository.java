package com.orangetalents.treinomercadolivre.repository;

import org.springframework.data.repository.CrudRepository;

import com.orangetalents.treinomercadolivre.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
