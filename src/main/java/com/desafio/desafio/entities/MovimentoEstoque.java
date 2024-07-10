package com.desafio.desafio.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.desafio.desafio.entities.enums.Movimentacao;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "tb_movimento_estoque")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovimentoEstoque implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produtoId;
    private LocalDateTime data_movimento;
    private Integer quantidade;
    @Enumerated(EnumType.STRING)
    private Movimentacao movimentacao;
}
