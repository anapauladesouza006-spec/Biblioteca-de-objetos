package view.menus;

import controle.ReservaControle;
import modelo.Reserva;
import view.screens.TelaCadastroBiblioteca;
import view.screens.TelaCadastroLeitor;
import view.screens.TelaCadastroLivro;
import view.screens.TelaCadastroSecretaria;
import view.screens.TelaEmprestimo;
import view.screens.TelaReserva;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class MenuSecretaria extends JFrame {
    private final int idSecretaria;

    public MenuSecretaria(int idSecretaria) {
        this.idSecretaria = idSecretaria;

        setTitle("Menu - Secretaria: " + this.idSecretaria);
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(8, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnCadastroSecretaria = new JButton("Cadastrar Secretaria");

        btnCadastroSecretaria.addActionListener(e -> {
            dispose();
            new TelaCadastroSecretaria(this.idSecretaria);
        });

        JButton btnCadastroLivro = new JButton("Cadastrar Livro");

        btnCadastroLivro.addActionListener(e -> {
            dispose();
            new TelaCadastroLivro(this.idSecretaria);
        });

        JButton btnCadastrarLeitor = new JButton("Cadastrar Leitor");

        btnCadastrarLeitor.addActionListener(e -> {
            dispose();
            new TelaCadastroLeitor(this.idSecretaria);
        });

        JButton btnEmprestar = new JButton("Realizar Empréstimo");

        btnEmprestar.addActionListener(e -> {
            dispose();
            new TelaEmprestimo(this.idSecretaria);
        });

        JButton btnReservar = new JButton("Realizar Reserva");

        btnReservar.addActionListener(e -> {
            dispose();
            new TelaReserva(this.idSecretaria);
        });

        JButton btnCadastrarBiblioteca = new JButton("Cadastrar Biblioteca");

        btnCadastrarBiblioteca.addActionListener(e -> {
            dispose();
            new TelaCadastroBiblioteca(this.idSecretaria);
        });

        JButton btnListarReserva = new JButton("Lista de Reservas");

        btnListarReserva.addActionListener(e -> listarReservas());

        JButton btnSair = new JButton("Sair");

        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        painel.add(btnCadastroSecretaria);
        painel.add(btnCadastrarLeitor);
        painel.add(btnCadastroLivro);
        painel.add(btnCadastrarBiblioteca);
        painel.add(btnEmprestar);
        painel.add(btnReservar);
        painel.add(btnListarReserva);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }

    private void listarReservas() {
        java.util.List<Reserva> reservas = ReservaControle.listarReservas();

        if (reservas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma reserva cadastrada.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        StringBuilder texto = new StringBuilder();

        for (Reserva reserva : reservas) {
            texto.append("ID: ")
                    .append(reserva.getid_reserva())
                    .append("\n");

            texto.append("Status: ")
                    .append(reserva.getStatus())
                    .append("\n");

            texto.append("Data de retirada: ")
                    .append(sdf.format(reserva.getData_retirada()))
                    .append("\n");

            texto.append("Livro: ")
                    .append(reserva.getLivro().getTitulo())
                    .append("\n");

            texto.append("Leitor: ")
                    .append(reserva.getLeitor().getNome())
                    .append("\n");

            texto.append("------------------------------\n");
        }

        JTextArea area = new JTextArea(texto.toString());
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(450, 350));

        JOptionPane.showMessageDialog(
                this,
                scroll,
                "Lista de Reservas",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}

