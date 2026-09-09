package view.screens;

import controle.LeitorControle;
import modelo.Leitor;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroLeitor extends JFrame {

    public TelaCadastroLeitor(int id_secretaria) {
        setTitle("Cadastro de Leitor");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();

        JLabel lblCpf = new JLabel("Cpf:");
        JTextField txtCpf = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> LeitorControle.cadastrarLeitor(
                txtNome.getText().trim(),
                txtTelefone.getText().trim(),
                txtCpf.getText().trim(),
                this,
                id_secretaria));

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new view.menus.MenuSecretaria(id_secretaria);
        });

        painel.add(lblNome);
        painel.add(txtNome);
        painel.add(lblTelefone);
        painel.add(txtTelefone);
        painel.add(lblCpf);
        painel.add(txtCpf);
        painel.add(btnVoltar);
        painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}
