package view.screens;

import controle.LivroControle;
import modelo.Livro;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCadastroLivro extends JFrame {

    private final int idSecretaria;

    public TelaCadastroLivro(int idSecretaria) {

        this.idSecretaria = idSecretaria;

        setTitle("Gerenciar Livros");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mostrarTela();

        setVisible(true);
    }

    private void mostrarTela() {

        JPanel painel = new JPanel(
                new GridLayout(7, 2, 10, 10));

        painel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("Título:");
        JTextField txtTitulo = new JTextField();

        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField();

        JLabel lblGenero = new JLabel("Gênero:");
        JTextField txtGenero = new JTextField();

        JLabel lblStatus = new JLabel("Status:");
        JTextField txtStatus = new JTextField("Disponivel");

        JButton btnCadastrar = new JButton("Cadastrar");

        btnCadastrar.addActionListener(e -> {

            LivroControle.cadastrarLivro(
                    txtTitulo.getText().trim(),
                    txtAutor.getText().trim(),
                    txtGenero.getText().trim(),
                    txtStatus.getText().trim(),
                    this,
                    idSecretaria);
        });

        JButton btnListar = new JButton("Listar");

        btnListar.addActionListener(e -> listarLivros());

        JButton btnAlterar = new JButton("Alterar");

        btnAlterar.addActionListener(e -> {

            String entrada = JOptionPane.showInputDialog(
                    this,
                    "Digite o ID do livro que deseja alterar:");

            if (entrada == null || entrada.trim().isEmpty()) {
                return;
            }

            try {

                int id = Integer.parseInt(entrada.trim());

                Livro livro = LivroControle.obterLivro(id);

                if (livro == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Livro não encontrado.");

                    return;
                }

                txtTitulo.setText(livro.getTitulo());
                txtAutor.setText(livro.getAutor());
                txtGenero.setText(livro.getGenero());
                txtStatus.setText(livro.getStatus());

                int resposta = JOptionPane.showConfirmDialog(
                        this,
                        "Deseja salvar as alterações deste livro?",
                        "Alterar livro",
                        JOptionPane.YES_NO_OPTION);

                if (resposta == JOptionPane.YES_OPTION) {

                    LivroControle.editarLivro(
                            id,
                            txtTitulo.getText().trim(),
                            txtAutor.getText().trim(),
                            txtGenero.getText().trim(),
                            txtStatus.getText().trim(),
                            this);
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite um ID válido.");
            }
        });

        JButton btnExcluir = new JButton("Excluir");

        btnExcluir.addActionListener(e -> {

            String entrada = JOptionPane.showInputDialog(
                    this,
                    "Digite o ID do livro que deseja excluir:");

            if (entrada == null || entrada.trim().isEmpty()) {
                return;
            }

            try {

                int id = Integer.parseInt(entrada.trim());

                Livro livro = LivroControle.obterLivro(id);

                if (livro == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Livro não encontrado.");

                    return;
                }

                LivroControle.excluirLivro(
                        id,
                        this);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite um ID válido.");
            }
        });

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> voltarMenu());

        painel.add(lblTitulo);
        painel.add(txtTitulo);

        painel.add(lblAutor);
        painel.add(txtAutor);

        painel.add(lblGenero);
        painel.add(txtGenero);

        painel.add(lblStatus);
        painel.add(txtStatus);

        painel.add(btnCadastrar);
        painel.add(btnListar);

        painel.add(btnAlterar);
        painel.add(btnExcluir);

        painel.add(btnVoltar);
        painel.add(new JLabel());

        add(painel);
    }

    private void listarLivros() {

        List<Livro> livros =
                LivroControle.listarLivros();

        if (livros.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Nenhum livro cadastrado.");

            return;
        }

        StringBuilder texto =
                new StringBuilder();

        for (Livro livro : livros) {

            texto.append("ID: ")
                    .append(livro.getId_livro())
                    .append("\n");

            texto.append("Título: ")
                    .append(livro.getTitulo())
                    .append("\n");

            texto.append("Autor: ")
                    .append(livro.getAutor())
                    .append("\n");

            texto.append("Gênero: ")
                    .append(livro.getGenero())
                    .append("\n");

            texto.append("Status: ")
                    .append(livro.getStatus())
                    .append("\n");

            texto.append("------------------------------\n");
        }

        JTextArea area =
                new JTextArea(texto.toString());

        area.setEditable(false);

        JScrollPane scroll =
                new JScrollPane(area);

        scroll.setPreferredSize(
                new Dimension(450, 350));

        JOptionPane.showMessageDialog(
                this,
                scroll,
                "Lista de Livros",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void voltarMenu() {

        dispose();

        new view.menus.MenuSecretaria(
                idSecretaria);
    }
}

