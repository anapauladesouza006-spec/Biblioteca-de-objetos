package view.screens;

import controle.EmprestimoControle;
import controle.ReservaControle;
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
        List<Livro> livros = manipuladorArquivos.lerLivros();
        List<Emprestimo> emprestimos = manipuladorArquivos.lerEmprestimos();

        JComboBox<Leitor> cmbLeitor = new JComboBox<>();
        JComboBox<Livro> cmbLivro = new JComboBox<>();

        JLabel lblEmprestimo= new JLabel("Data da retirada:");
        JLabel lblEmprestimo1 = new JLabel("");

        JLabel lblStatus= new JLabel("Status:");
        JLabel txtStatus1 = new JLabel("");


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



        if(idSecretaria == 0 && id_leitor != 0){
                    for(Emprestimo emp : emprestimos){
                        if(emp.getLeitor().getId_leitor() == id_leitor ){
                            cmbLivro.addItem(emp.getLivro());
                        }
                    }
        }
        else if(idSecretaria != 0 && id_leitor == 0){
            Leitor leitor = (Leitor) cmbLeitor.getSelectedItem();
            for(Emprestimo emp : emprestimos){
                if(emp.getLeitor().getId_leitor() == leitor.getId_leitor()){
                    cmbLivro.addItem(emp.getLivro());
                }
            }
        }

        cmbLeitor.addActionListener(e ->{
            cmbLivro.removeAllItems();
            lblEmprestimo1.setText("");
            txtStatus1.setText("");

            Leitor leitor = (Leitor) cmbLeitor.getSelectedItem();
            for(Emprestimo emp : emprestimos){
                if(emp.getLeitor().getId_leitor() == leitor.getId_leitor()){
                    cmbLivro.addItem(emp.getLivro());
                }
            }
        });

        cmbLivro.addActionListener(e ->{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Leitor leitor = (Leitor) cmbLeitor.getSelectedItem();
                Livro livro = (Livro) cmbLivro.getSelectedItem();
                try {
                    Emprestimo emprestimosLeitor = EmprestimoControle.buscarEmprestimo(leitor.getId_leitor(), livro.getId_livro());

                    Date hoje = new Date();

                    lblEmprestimo1.setText(sdf.format(emprestimosLeitor.getData_devolucao()));
                    if(emprestimosLeitor.getData_devolucao().before(hoje)){
                        txtStatus1.setText("Em atraso");
                    }
                    else{
                        txtStatus1.setText("Dentro do prazo");
                    }

                } catch(NullPointerException err){
                    System.err.println("Erro em listar devolução: " + err);
                }


        });



        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
            if(idSecretaria != 0 && id_leitor == 0) {
                new MenuSecretaria(idSecretaria);
            }
            else if(idSecretaria == 0 && id_leitor != 0){
                new MenuLeitor(id_leitor);
            }
        });

        JButton btnDevolver = new JButton("Devolver");

        btnDevolver.addActionListener(e -> {
            if (cmbLeitor.getSelectedItem() == null || cmbLivro.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Cadastre leitores e livros antes de realizar a reserva."
                );
                return;
            }
        });

        painel.add(btnVoltar);
        painel.add(btnDevolver);

        add(painel);
        setVisible(true);
    }
}

