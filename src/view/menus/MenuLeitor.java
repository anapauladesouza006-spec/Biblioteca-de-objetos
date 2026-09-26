package view.menus;

import controle.BibliotecaControle;
import controle.LivroControle;
import modelo.Biblioteca;
import modelo.Livro;
import util.manipuladorArquivos;
import view.screens.TelaReserva;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuLeitor extends JFrame {
    private int id_leitor;

    public MenuLeitor(int id_leitor) {
        this.id_leitor = id_leitor;

        setTitle("Menu Leitor - " + this.id_leitor);
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnListarLivros = new JButton("Listar Livros");

        btnListarLivros.addActionListener(e -> {
            mostrarLista(id_leitor);
        });

        JButton btnReservar = new JButton("Cadastrar Reserva");
        btnReservar.addActionListener(e ->{
            dispose();
            new TelaReserva(0, this.id_leitor);
        });

        JButton btnDevolver = new JButton("Realizar Devolução");

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial(); 
        });

        painel.add(btnListarLivros);
        painel.add(btnReservar);
        painel.add(btnDevolver);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }

    private void mostrarLista(int id_leitor) {

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        painel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel(
                "Livros disponíveis",
                SwingConstants.CENTER);

        titulo.setFont(
                new Font("Arial", Font.BOLD, 18));

        JTextArea areaLista = new JTextArea();

        areaLista.setEditable(false);

        List<Livro> livros = manipuladorArquivos.lerLivros();

        if (livros.isEmpty()) {

            areaLista.setText(
                    "Nenhum livro disponível.");

        } else {

            StringBuilder texto = new StringBuilder();

            /*private int id_livro;
            private String titulo;
            private String autor;
            private String genero;
            private String status;*/

            for (Livro livro : livros) {

                texto.append("ID: ")
                        .append(livro.getId_livro())
                        .append("\n");

                texto.append("Título: ")
                        .append(livro.getTitulo())
                        .append("\n");

                texto.append("Escrito por ")
                        .append(livro.getAutor())
                        .append("\n");

                texto.append("Gênero: ")
                        .append(livro.getGenero())
                        .append("\n");

                texto.append("Status de reserva: ")
                        .append(livro.getStatus())
                        .append("\n");

                texto.append("-----------------------------\n");
            }

            areaLista.setText(texto.toString());
        }

        JScrollPane scroll = new JScrollPane(areaLista);

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e ->{
            new MenuLeitor(id_leitor);
            dispose();});

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);
        painel.add(btnVoltar, BorderLayout.SOUTH);

        setContentPane(painel);

        revalidate();
        repaint();
    }
}
