package visao.telas;

import controle.PacienteControle;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroPaciente extends JFrame {

    public TelaCadastroPaciente(int idSecretaria) {
        setTitle("Cadastro de Paciente");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblDataNascimento = new JLabel("Data Nasc. (dd/MM/yyyy):");
        JTextField txtDataNascimento = new JTextField();

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> 
            PacienteControle.cadastrarPaciente(
                txtNome.getText().trim(),
                txtDataNascimento.getText().trim(),
                txtTelefone.getText().trim(),
                txtEmail.getText().trim(),
                this,
                idSecretaria
            )
        );

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuSecretaria(idSecretaria); 
        });

        painel.add(lblNome); painel.add(txtNome);
        painel.add(lblDataNascimento); painel.add(txtDataNascimento);
        painel.add(lblTelefone); painel.add(txtTelefone);
        painel.add(lblEmail); painel.add(txtEmail);
        painel.add(btnVoltar); painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}

