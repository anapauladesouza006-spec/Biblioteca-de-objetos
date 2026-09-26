package view.screens;

import controle.ReservaControle;
import modelo.Leitor;
import modelo.Livro;
import util.manipuladorArquivos;
import view.menus.MenuLeitor;
import view.menus.MenuSecretaria;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaDevolucao extends JFrame {
    public TelaDevolucao(int idSecretaria, int id_leitor) {
        setTitle("Realizar Reserva");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Leitor> leitores = manipuladorArquivos.lerLeitores();
        List<Livro> livros = manipuladorArquivos.lerLivros();

        JComboBox<Leitor> cmbLeitor = new JComboBox<>();

        if(idSecretaria == 0 && id_leitor != 0){
            for (Leitor leitor : leitores) {
                if(leitor.getId_leitor() == id_leitor){
                    cmbLeitor.addItem(leitor);
                }
            }
        }
        else if(idSecretaria != 0 && id_leitor == 0){
            for (Leitor leitor : leitores) {
                cmbLeitor.addItem(leitor);
            }
        }

        JComboBox<Livro> cmbLivro = new JComboBox<>();

        for (Livro livro : livros) {
            cmbLivro.addItem(livro);
        }

        JTextField txtRetirada = new JTextField();

        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(new JLabel("Leitor:"));
        painel.add(cmbLeitor);

        painel.add(new JLabel("Livro:"));
        painel.add(cmbLivro);

        painel.add(new JLabel("Data da retirada:"));
        painel.add(txtRetirada);

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {
            dispose();
            if(idSecretaria != 0 && id_leitor == 0) {
                new MenuSecretaria(idSecretaria);
            }
            else if(idSecretaria == 0 && id_leitor != 0){
                new MenuLeitor(id_leitor);
            }
        });

        JButton btnReservar = new JButton("Reservar");

        btnReservar.addActionListener(e -> {
            if (cmbLeitor.getSelectedItem() == null || cmbLivro.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Cadastre leitores e livros antes de realizar a reserva."
                );
                return;
            }

            Leitor leitor = (Leitor) cmbLeitor.getSelectedItem();
            Livro livro = (Livro) cmbLivro.getSelectedItem();

            String data = txtRetirada.getText().trim();

            ReservaControle.cadastrarReserva(
                    data,
                    livro.getId_livro(),
                    leitor.getId_leitor(),
                    "Ativa",
                    this,
                    idSecretaria
            );
        });

        painel.add(btnVoltar);
        painel.add(btnReservar);

        add(painel);
        setVisible(true);
    }
}

