package view.screens;

import controle.LivroControle;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroLivro extends JFrame {

    public TelaCadastroLivro(int idSecretaria) {
        setTitle("Cadastro de Livro");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem

        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField();

        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField();

        JLabel lblGenero = new JLabel("Gênero:");
        JTextField txtGenero = new JTextField();

        JLabel lblStatus = new JLabel("Status:");
        JTextField txtStatus = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> LivroControle.cadastrarLivro(
                txtTitulo.getText().trim(),
                txtAutor.getText().trim(),
                txtGenero.getText().trim(),
                txtStatus.getText().trim(),
                this,
                idSecretaria));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new view.menus.MenuInicial();
        });

        painel.add(lblTitulo);
        painel.add(txtTitulo);
        painel.add(lblAutor);
        painel.add(txtAutor);
        painel.add(lblGenero);
        painel.add(txtGenero);
        painel.add(lblStatus);
        painel.add(txtStatus);
        painel.add(btnVoltar);
        painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}
