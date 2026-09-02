package visao.telas;

import controle.ClinicaControle;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroClinica extends JFrame {

    public TelaCadastroClinica() {
        setTitle("Cadastro de Clínica");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margem

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblEndereco = new JLabel("Endereço:");
        JTextField txtEndereco = new JTextField();

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e ->
            ClinicaControle.cadastrarClinica(
                txtNome.getText().trim(),
                txtEndereco.getText().trim(),
                txtTelefone.getText().trim(),
                this
            )
        );

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuClinica();
        });

        painel.add(lblNome); painel.add(txtNome);
        painel.add(lblEndereco); painel.add(txtEndereco);
        painel.add(lblTelefone); painel.add(txtTelefone);
        painel.add(btnVoltar); painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}
