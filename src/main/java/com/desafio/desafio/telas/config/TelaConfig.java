package com.desafio.desafio.telas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.desafio.desafio.telas.telasDois.TelaEstoque;
import com.desafio.desafio.telas.telasUm.TelaAtualizar;
import com.desafio.desafio.telas.telasUm.TelaInserir;

@Configuration
public class TelaConfig {
    @Bean
    public TelaInserir tela() {
        TelaInserir tela = new TelaInserir();
        tela.exibirTela();
        return tela;
    }

    @Bean
    public TelaAtualizar tela2() {
        return new TelaAtualizar();
    }

    @Bean
    public TelaEstoque estoque() {
        return new TelaEstoque();
    }
}
