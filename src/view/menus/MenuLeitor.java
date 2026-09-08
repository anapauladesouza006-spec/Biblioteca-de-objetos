package view.menus;

import javax.swing.*;

import java.awt.*;

public class MenuLeitor extends JFrame {
    private int id_leitor;

    public MenuLeitor(int id_leitor) {
        this.id_leitor = id_leitor;

        setTitle("Menu Leitor - "+this.id_leitor);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnReserva = new JButton("Reservas");

        JButton btnReservar = new JButton("Atualizar reserva");

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial(); 
        });

        painel.add(btnReserva);
        painel.add(btnReservar);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}
