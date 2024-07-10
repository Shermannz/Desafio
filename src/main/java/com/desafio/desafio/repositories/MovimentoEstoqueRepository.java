package com.desafio.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.desafio.entities.MovimentoEstoque;

@Repository
public interface MovimentoEstoqueRepository extends JpaRepository<MovimentoEstoque, Long>{
    
}
