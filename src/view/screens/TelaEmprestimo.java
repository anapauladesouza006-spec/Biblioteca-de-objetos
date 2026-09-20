package view.screens;

import java.util.List;

import javax.swing.*;

import controle.EmprestimoControle;

import java.awt.*;
import modelo.Leitor;
import modelo.Livro;
import util.manipuladorArquivos;
import view.menus.MenuSecretaria;

public class TelaEmprestimo extends JFrame {
    public TelaEmprestimo(int idSecretaria) {
        setTitle("Realizar Empréstimo");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Leitor> leitores = manipuladorArquivos.lerLeitores();
        List<Livro> livros = manipuladorArquivos.lerLivros();
        JComboBox<Leitor> cmbLeitor = new JComboBox<>();
        for (Leitor lei : leitores) {
            cmbLeitor.addItem(lei);
        }
        JComboBox<Livro> cmbLivro = new JComboBox<>();
        for (Livro liv : livros) {
            cmbLivro.addItem(liv);
        }
        JTextField txtData = new JTextField();
       

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(new JLabel("Leitor:"));
        painel.add(cmbLeitor);

        painel.add(new JLabel("Livro:"));
        painel.add(cmbLivro);


        JButton btnAgendar = new JButton("Emprestar");
        btnAgendar.addActionListener(e -> {
            System.out.println(cmbLeitor.getSelectedItem().toString().split("-")[0]);
            int id_leitor = Integer.parseInt(cmbLeitor.getSelectedItem().toString().split("-")[0]);
            int id_livro = Integer.parseInt(cmbLivro.getSelectedItem().toString().split("-")[0]);
            

            EmprestimoControle.cadastrarEmprestimo(id_leitor, id_livro, idSecretaria, this);
        });

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuSecretaria(idSecretaria);
        });

        painel.add(btnVoltar);
        painel.add(btnAgendar);

        add(painel);
        setVisible(true);
    }

}
