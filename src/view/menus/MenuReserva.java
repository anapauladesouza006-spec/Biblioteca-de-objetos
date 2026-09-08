package view.menus;

import javax.swing.*;
import java.awt.*;

public class MenuReserva extends JFrame {
    private int id_reserva;

    public MenuReserva(int id_reserva) {
        this.id_reserva = id_reserva;

        setTitle("Menu Reserva - "+this.id_reserva);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnReservar = new JButton("Realizar Reserva");

        JButton btnDevolver = new JButton("Realizar Devolução");


        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial(); 
        });

        painel.add(btnReservar);
        painel.add(btnDevolver);
        
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }
}
