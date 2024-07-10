package com.desafio.desafio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.desafio.entities.MovimentoEstoque;
import com.desafio.desafio.entities.Produto;
import com.desafio.desafio.repositories.MovimentoEstoqueRepository;
import com.desafio.desafio.repositories.ProdutoRepository;

@Service
public class EstoqueService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentoEstoqueRepository movimentoEstoqueRepository;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public MovimentoEstoque registrarMovimento(MovimentoEstoque movimentoEstoque) {
        return movimentoEstoqueRepository.save(movimentoEstoque);
    }

    public List<MovimentoEstoque> listarMovimentos() {
        return movimentoEstoqueRepository.findAll();
    }
}