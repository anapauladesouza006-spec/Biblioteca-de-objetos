package visao.menus;

import javax.swing.*;

import visao.telas.TelaCadastroClinica;
import visao.telas.TelaCadastroMedico;
import visao.telas.TelaCadastroSecretaria;

import java.awt.*;

public class MenuClinica extends JFrame {

    public MenuClinica() {
        setTitle("Menu Clínica");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnClinica = new JButton("Cadastrar/Atualizar Clínica");
        btnClinica.addActionListener(e -> {
            dispose();
            new TelaCadastroClinica();
        });

        JButton btnCadastrarMedico = new JButton("Cadastrar Médico");
        btnCadastrarMedico.addActionListener(e -> {
            dispose();
            new TelaCadastroMedico();
        });

        JButton btnSecretaria = new JButton("Cadastrar Secretaria");
        btnSecretaria.addActionListener(e -> {
            dispose();
            new TelaCadastroSecretaria();
        });

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial(); 
        });
        painel.add(btnClinica);
        painel.add(btnCadastrarMedico);
        painel.add(btnSecretaria);

        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}
