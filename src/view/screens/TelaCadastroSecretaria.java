package view.screens;

import controle.SecretariaControle;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroSecretaria extends JFrame {

    public TelaCadastroSecretaria() {
        setTitle("Cadastro de Secretaria");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblCargo = new JLabel("Cargo:");
        JTextField txtCargo = new JTextField();

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e ->
            SecretariaControle.cadastrarSecretaria(
                txtNome.getText().trim(),
                txtCargo.getText().trim(),
                txtTelefone.getText().trim(),
                txtEmail.getText().trim(),
                this
            )
        );

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new view.menus.MenuBiblioteca();
        });

        painel.add(lblNome); painel.add(txtNome);
        painel.add(lblCargo); painel.add(txtCargo);
        painel.add(lblTelefone); painel.add(txtTelefone);
        painel.add(lblEmail); painel.add(txtEmail);
        painel.add(btnVoltar); painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}
