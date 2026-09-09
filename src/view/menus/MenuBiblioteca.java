package view.menus;

import javax.swing.*;

import view.screens.*;

import java.awt.*;

public class MenuBiblioteca extends JFrame {

    public MenuBiblioteca() {
        setTitle("Menu Biblioteca");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        /*JButton btnLivro = new JButton("Cadastrar/Atualizar Livro");
        btnLivro.addActionListener(e -> {
            dispose();
            new TelaCadastroLivro();
        });

        JButton btnCadastrarLeitor = new JButton("Cadastrar Leitor");
        btnCadastrarLeitor.addActionListener(e -> {
            dispose();
            new TelaCadastroLeitor();
        });*/

        JButton btnSecretaria = new JButton("Cadastrar Secretaria");
        btnSecretaria.addActionListener(e -> {
            dispose();
            new TelaCadastroSecretaria();
        });

        JButton btnBiblioteca = new JButton("Cadastrar Biblioteca");
        btnBiblioteca.addActionListener(e -> {
            dispose();
            new TelaCadastroBiblioteca();
        });

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });
        /*painel.add(btnLivro);
        painel.add(btnCadastrarLeitor);*/
        painel.add(btnSecretaria);
        painel.add(btnBiblioteca);

        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}
