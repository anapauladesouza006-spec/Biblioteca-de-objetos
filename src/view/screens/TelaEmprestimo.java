package view.screens;

import controle.EmprestimoControle;
import modelo.Leitor;
import modelo.Livro;
import util.manipuladorArquivos;
import view.menus.MenuSecretaria;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class TelaEmprestimo extends JFrame {
    public TelaEmprestimo(int idSecretaria) {
        setTitle("Realizar Empréstimo");
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Leitor> leitores = manipuladorArquivos.lerLeitores();

        List<Livro> livros = manipuladorArquivos.lerLivros()
                .stream()
                .filter(l -> !l.getStatus().equalsIgnoreCase("Emprestado"))
                .toList();

        JComboBox<Leitor> cmbLeitor = new JComboBox<>();

        for (Leitor leitor : leitores) {
            cmbLeitor.addItem(leitor);
        }

        JComboBox<Livro> cmbLivro = new JComboBox<>();

        for (Livro livro : livros) {
            cmbLivro.addItem(livro);
        }

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(new JLabel("Leitor:"));
        painel.add(cmbLeitor);

        painel.add(new JLabel("Livro:"));
        painel.add(cmbLivro);

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuSecretaria(idSecretaria);
        });

        JButton btnEmprestar = new JButton("Emprestar");

        btnEmprestar.addActionListener(e -> {
            if (cmbLeitor.getSelectedItem() == null || cmbLivro.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Cadastre leitores e livros disponíveis antes de realizar o empréstimo."
                );
                return;
            }

            Leitor leitor = (Leitor) cmbLeitor.getSelectedItem();
            Livro livro = (Livro) cmbLivro.getSelectedItem();

            EmprestimoControle.cadastrarEmprestimo(
                    livro.getId_livro(),
                    idSecretaria,
                    leitor.getId_leitor(),
                    this
            );
        });

        painel.add(btnVoltar);
        painel.add(btnEmprestar);

        add(painel);
        setVisible(true);
    }
}

