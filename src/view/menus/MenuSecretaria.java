package visao.menus;

import javax.swing.*;

import visao.telas.TelaAgendamentoConsulta;
import visao.telas.TelaCadastroPaciente;


import java.awt.*;

public class MenuSecretaria extends JFrame {
    private int idSecretaria;

    public MenuSecretaria(int idSecretaria) {
        this.idSecretaria = idSecretaria;
        setTitle("Menu - Secretaria: "+this.idSecretaria);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnCadastro = new JButton("Cadastrar Paciente");
        btnCadastro.addActionListener(e -> {
            dispose(); // Fecha o menu
            new TelaCadastroPaciente(idSecretaria);
        });

        JButton btnAtualizar = new JButton("Atualizar Contato do Paciente");


        JButton btnAgendar = new JButton("Agendar Consulta");
        btnAgendar.addActionListener(e -> {
            dispose(); // Fecha o menu
            new TelaAgendamentoConsulta(this.idSecretaria);
        });

        JButton btnListarAgendamentos = new JButton("Lista de Agendamentos");

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        painel.add(btnCadastro);
        painel.add(btnAtualizar);
        painel.add(btnAgendar);
        painel.add(btnListarAgendamentos);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}
