package com.desafio.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.desafio.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
