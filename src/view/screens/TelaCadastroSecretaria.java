package visao.telas;

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

        JLabel lblTurno = new JLabel("Turno:");
        JTextField txtTurno = new JTextField();

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e ->
            SecretariaControle.cadastrarSecretaria(
                txtNome.getText().trim(),
                txtTurno.getText().trim(),
                this
            )
        );

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose();
            new visao.menus.MenuClinica();
        });

        painel.add(lblNome); painel.add(txtNome);
        painel.add(lblTurno); painel.add(txtTurno);
        painel.add(btnVoltar); painel.add(btnSalvar);

        add(painel);
        setVisible(true);
    }
}
