package com.desafio.desafio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.desafio.entities.Produto;
import com.desafio.desafio.repositories.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public Produto findById(Long id){
        return repository.findById(id).get();
    }

    public Produto insert(Produto dto) {
        return repository.save(dto);
    }

    public Produto update(Produto dto, Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
        dto.setId(produto.getId());
        return repository.save(dto);
    }
}
