package view.screens;

import controle.EmprestimoControle;
import modelo.Emprestimo;
import modelo.Leitor;
import modelo.Livro;
import util.manipuladorArquivos;
import view.menus.MenuLeitor;
import view.menus.MenuSecretaria;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TelaDevolucao extends JFrame {

    public TelaDevolucao(int idSecretaria, int id_leitor) {

        setTitle("Realizar Devolução");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Leitor> leitores = manipuladorArquivos.lerLeitores();
        List<Emprestimo> emprestimos = manipuladorArquivos.lerEmprestimos();

        JComboBox<Leitor> cmbLeitor = new JComboBox<>();
        JComboBox<Livro> cmbLivro = new JComboBox<>();

        JLabel lblEmprestimo = new JLabel("Data de devolução:");
        JLabel lblEmprestimo1 = new JLabel("");

        JLabel lblStatus = new JLabel("Status:");
        JLabel txtStatus1 = new JLabel("");

        if (idSecretaria == 0 && id_leitor != 0) {

            for (Leitor leitor : leitores) {

                if (leitor.getId_leitor() == id_leitor) {
                    cmbLeitor.addItem(leitor);
                }
            }

        } else if (idSecretaria != 0 && id_leitor == 0) {

            for (Leitor leitor : leitores) {
                cmbLeitor.addItem(leitor);
            }
        }

        if (idSecretaria == 0 && id_leitor != 0) {

            for (Emprestimo emp : emprestimos) {

                if (emp.getLeitor().getId_leitor() == id_leitor
                        && emp.getAtiva()) {

                    cmbLivro.addItem(emp.getLivro());
                }
            }

        } else if (idSecretaria != 0 && id_leitor == 0) {

            Leitor leitor = (Leitor) cmbLeitor.getSelectedItem();

            if (leitor != null) {

                for (Emprestimo emp : emprestimos) {

                    if (emp.getLeitor().getId_leitor() == leitor.getId_leitor()
                            && emp.getAtiva()) {

                        cmbLivro.addItem(emp.getLivro());
                    }
                }
            }
        }

        cmbLeitor.addActionListener(e -> {

            cmbLivro.removeAllItems();

            lblEmprestimo1.setText("");
            txtStatus1.setText("");

            Leitor leitor = (Leitor) cmbLeitor.getSelectedItem();

            if (leitor == null) {
                return;
            }

            for (Emprestimo emp : emprestimos) {

                if (emp.getLeitor().getId_leitor() == leitor.getId_leitor()
                        && emp.getAtiva()) {

                    cmbLivro.addItem(emp.getLivro());
                }
            }
        });

        cmbLivro.addActionListener(e -> {

            Leitor leitor = (Leitor) cmbLeitor.getSelectedItem();
            Livro livro = (Livro) cmbLivro.getSelectedItem();

            if (leitor == null || livro == null) {
                lblEmprestimo1.setText("");
                txtStatus1.setText("");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            Emprestimo emprestimo = EmprestimoControle.buscarEmprestimo(
                    leitor.getId_leitor(),
                    livro.getId_livro()
            );

            if (emprestimo == null) {
                lblEmprestimo1.setText("");
                txtStatus1.setText("Empréstimo não encontrado.");
                return;
            }

            Date hoje = new Date();

            lblEmprestimo1.setText(
                    sdf.format(emprestimo.getData_devolucao())
            );

            if (emprestimo.getData_devolucao().before(hoje)) {
                txtStatus1.setText("Em atraso");
            } else {
                txtStatus1.setText("Dentro do prazo");
            }
        });

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        painel.add(new JLabel("Leitor:"));
        painel.add(cmbLeitor);

        painel.add(new JLabel("Livro:"));
        painel.add(cmbLivro);

        painel.add(lblEmprestimo);
        painel.add(lblEmprestimo1);

        painel.add(lblStatus);
        painel.add(txtStatus1);

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {

            dispose();

            if (idSecretaria != 0 && id_leitor == 0) {
                new MenuSecretaria(idSecretaria);

            } else if (idSecretaria == 0 && id_leitor != 0) {
                new MenuLeitor(id_leitor);
            }
        });

        JButton btnDevolver = new JButton("Devolver");

        btnDevolver.addActionListener(e -> {

            if (cmbLeitor.getSelectedItem() == null
                    || cmbLivro.getSelectedItem() == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecione um leitor e um livro."
                );

                return;
            }

            Leitor leitor =
                    (Leitor) cmbLeitor.getSelectedItem();

            Livro livro =
                    (Livro) cmbLivro.getSelectedItem();

            boolean devolvido =
                    EmprestimoControle.devolverEmprestimo(
                            leitor.getId_leitor(),
                            livro.getId_livro(),
                            this
                    );

            if (devolvido) {

                dispose();

                if (idSecretaria != 0 && id_leitor == 0) {
                    new MenuSecretaria(idSecretaria);

                } else if (idSecretaria == 0 && id_leitor != 0) {
                    new MenuLeitor(id_leitor);
                }
            }
        });

        painel.add(btnVoltar);
        painel.add(btnDevolver);

        add(painel);

        setVisible(true);
    }
}