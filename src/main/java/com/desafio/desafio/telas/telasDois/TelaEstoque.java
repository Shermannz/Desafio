package com.desafio.desafio.telas.telasDois;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.desafio.entities.MovimentoEstoque;
import com.desafio.desafio.entities.Produto;
import com.desafio.desafio.entities.enums.Movimentacao;
import com.desafio.desafio.services.EstoqueService;
import com.desafio.desafio.services.ProdutoService;
import com.desafio.desafio.telas.telasUm.TelaAtualizar;

public class TelaEstoque extends JFrame {

    @Autowired
    private EstoqueService service;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private TelaAtualizar telaAtualizar;
    private JTextField idField;
    private JTextField quantidadeField;
    private JComboBox<String> tipoMovimentacaoBox;
    private JPanel panel = new JPanel();

    public TelaEstoque() {
        configurarInterface();
        adicionarComponentes();
    }

    private void configurarInterface() {
        setTitle("Tela Estoque");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void adicionarComponentes() {
        JPanel formPanel = new JPanel(new GridLayout(5, 2));

        formPanel.add(new JLabel("Id do Produto:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Quantidade:"));
        quantidadeField = new JTextField();
        formPanel.add(quantidadeField);

        formPanel.add(new JLabel("Tipo Movimentação:"));
        tipoMovimentacaoBox = new JComboBox<>(new String[] { "ENTRADA", "SAIDA" });
        formPanel.add(tipoMovimentacaoBox);

        JButton addButton = new JButton("Registrar Entrada/Saida");
        JButton listButton = new JButton("Retornar para a tela Atualizar");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long id = Long.parseLong(idField.getText());
                LocalDateTime localDateTime = LocalDateTime.now();
                Integer quantidade = Integer.parseInt(quantidadeField.getText());
                Movimentacao moviEnum;
                String tipoMovimentacaoSelecionada = (String) tipoMovimentacaoBox.getSelectedItem();
                if (tipoMovimentacaoSelecionada.equalsIgnoreCase("ENTRADA")) {
                    moviEnum = Movimentacao.ENTRADA;
                } else {
                    moviEnum = Movimentacao.SAIDA;
                }
                Produto p = produtoService.findById(id);
                MovimentoEstoque estoque = new MovimentoEstoque(null, p, localDateTime, quantidade, moviEnum);
                service.registrarMovimento(estoque);

            }
        });

        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocultarTela();
                telaAtualizar.exibirTela();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(listButton);

        panel.setLayout(new BorderLayout());
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
    }

    public void ocultarTela() {
        setVisible(false);
    }

    public void exibirTela() {
        getContentPane().add(panel);
        setVisible(true);
    }
}