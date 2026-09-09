package view.menus;

import javax.swing.*;

import view.screens.*;


import java.awt.*;

public class MenuSecretaria extends JFrame {
    private int id_secretaria;

    public MenuSecretaria(int id_secretaria) {
        this.id_secretaria = id_secretaria;
        setTitle("Menu - Secretaria: "+this.id_secretaria);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnCadastroSecretaria = new JButton("Cadastrar Secretaria");
        btnCadastroSecretaria.addActionListener(e -> {
            dispose(); // Fecha o menu
            new TelaCadastroSecretaria();
        });

        JButton btnAtualizar = new JButton("Atualizar Contato do Leitor");


        JButton btnCadastrarLeitor = new JButton("Cadastrar Leitor");
        btnCadastrarLeitor.addActionListener(e -> {
            dispose(); // Fecha o menu
            new TelaCadastroLeitor(this.id_secretaria);
        });

        JButton btnCadastrarLivro = new JButton("Cadastrar Livro");
        btnCadastrarLivro.addActionListener(e -> {
            dispose(); // Fecha o menu
            new TelaCadastroLivro(this.id_secretaria);
        });

        JButton btnReservar = new JButton("Realizar Reserva");
        btnReservar.addActionListener(e -> {
            dispose(); // Fecha o menu
            new TelaReserva(this.id_secretaria);
        });

        JButton btnListarReserva = new JButton("Lista de Reservas");

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        painel.add(btnCadastroSecretaria);
        painel.add(btnCadastrarLeitor);
        painel.add(btnAtualizar);
        painel.add(btnReservar);
        painel.add(btnListarReserva);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}
