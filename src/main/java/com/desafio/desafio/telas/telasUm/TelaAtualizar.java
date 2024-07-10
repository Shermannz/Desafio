package com.desafio.desafio.telas.telasUm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;

import com.desafio.desafio.entities.Produto;
import com.desafio.desafio.services.ProdutoService;
import com.desafio.desafio.telas.telasDois.TelaEstoque;

public class TelaAtualizar extends JFrame {

    @Autowired
    private ProdutoService service;
    @Autowired
    private TelaInserir telaInserir;
    @Autowired
    private TelaEstoque telaEstoque;
    private JTextField id = new JTextField(20);
    private JTextField descricao = new JTextField(20);
    private JTextField quantidadeMinima = new JTextField(20);
    private JTextField dataCadastro = new JTextField(20);
    private JTextField valor = new JTextField(20);
    private JPanel panel = new JPanel();

    public TelaAtualizar() {
        configurarInterface();
        adicionarComponentes();
    }

    private void configurarInterface() {
        setTitle("Atualizar Produtos");
        setSize(400, 255);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void adicionarComponentes() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        adicionarCampo(formPanel, "Id:", id);
        adicionarCampo(formPanel, "Descrição:", descricao);
        adicionarCampo(formPanel, "Quantidade Mínima:", quantidadeMinima);
        adicionarCampo(formPanel, "Data de Cadastro:", dataCadastro);
        adicionarCampo(formPanel, "Valor:", valor);

        JButton addButton = new JButton("Atualizar");
        JButton listButton = new JButton("Retornar para a tela Inserir");
        JButton listButton2 = new JButton("Ir para a Tela de Estoque");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Long idVariavel = Long.parseLong(id.getText());
                String desc = descricao.getText();
                int quantidade = Integer.parseInt(quantidadeMinima.getText());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataString = dataCadastro.getText();
                LocalDateTime localDateTime = LocalDate.parse(dataString, formatter).atStartOfDay();

                double val = Double.parseDouble(valor.getText());
                Produto p = new Produto(idVariavel, desc, quantidade, localDateTime, val);
                service.update(p, idVariavel);
            }

        });
        listButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ocultarTela();
                telaInserir.exibirTela();
            }

        });
        listButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ocultarTela();
                telaEstoque.exibirTela();
            }

        });
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(addButton);
        buttonPanel.add(listButton);
        buttonPanel.add(listButton2);

        panel.setLayout(new BorderLayout());
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
    }

    private void adicionarCampo(JPanel panel, String label, JComponent component) {
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbl = new JLabel(label);
        lbl.setPreferredSize(new Dimension(150, 20));
        fieldPanel.add(lbl);
        fieldPanel.add(component);
        panel.add(fieldPanel);
    }

    public void ocultarTela() {
        getContentPane().add(panel);
        setVisible(false);
    }

    public void exibirTela() {
        getContentPane().add(panel);
        setVisible(true);
    }
}