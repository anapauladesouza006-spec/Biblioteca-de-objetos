
package view.screens;

import controle.BibliotecaControle;
import modelo.Biblioteca;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCadastroBiblioteca extends JFrame {

    private int idSecretaria;

    public TelaCadastroBiblioteca(int idSecretaria) {

        this.idSecretaria = idSecretaria;

        setTitle("Biblioteca");
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mostrarCadastro();

        setVisible(true);
    }

    private void mostrarCadastro() {

        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));

        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNome = new JLabel("Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblEndereco = new JLabel("Endereço:");
        JTextField txtEndereco = new JTextField();

        JLabel lblTelefone = new JLabel("Telefone:");
        JTextField txtTelefone = new JTextField();

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.addActionListener(e -> BibliotecaControle.cadastrarBiblioteca(
                txtNome.getText().trim(),
                txtEndereco.getText().trim(),
                txtTelefone.getText().trim(),
                this,
                idSecretaria));

        JButton btnListar = new JButton("Listar");

        btnListar.addActionListener(e -> mostrarLista());

        JButton btnAlterar = new JButton("Alterar");

        btnAlterar.addActionListener(e -> mostrarAlteracao());

        JButton btnExcluir = new JButton("Excluir");

        btnExcluir.addActionListener(e -> mostrarExclusao());

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> voltarMenu());

        painel.add(lblNome);
        painel.add(txtNome);

        painel.add(lblEndereco);
        painel.add(txtEndereco);

        painel.add(lblTelefone);
        painel.add(txtTelefone);

        painel.add(btnSalvar);
        painel.add(btnListar);

        painel.add(btnAlterar);
        painel.add(btnExcluir);

        painel.add(new JLabel());
        painel.add(btnVoltar);

        setContentPane(painel);

        revalidate();
        repaint();
    }

    private void mostrarLista() {

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        painel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel(
                "Bibliotecas cadastradas",
                SwingConstants.CENTER);

        titulo.setFont(
                new Font("Arial", Font.BOLD, 18));

        JTextArea areaLista = new JTextArea();

        areaLista.setEditable(false);

        List<Biblioteca> bibliotecas = BibliotecaControle.listarBibliotecas();

        if (bibliotecas.isEmpty()) {

            areaLista.setText(
                    "Nenhuma biblioteca cadastrada.");

        } else {

            StringBuilder texto = new StringBuilder();

            for (Biblioteca biblioteca : bibliotecas) {

                texto.append("ID: ")
                        .append(biblioteca.getId_biblioteca())
                        .append("\n");

                texto.append("Nome: ")
                        .append(biblioteca.getNomeBiblioteca())
                        .append("\n");

                texto.append("Endereço: ")
                        .append(biblioteca.getEndereco())
                        .append("\n");

                texto.append("Telefone: ")
                        .append(biblioteca.getTelefone())
                        .append("\n");

                texto.append("-----------------------------\n");
            }

            areaLista.setText(texto.toString());
        }

        JScrollPane scroll = new JScrollPane(areaLista);

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> mostrarCadastro());

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);
        painel.add(btnVoltar, BorderLayout.SOUTH);

        setContentPane(painel);

        revalidate();
        repaint();
    }

    private void mostrarAlteracao() {

        JPanel painel = new JPanel(
                new GridLayout(6, 2, 10, 10));

        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblId = new JLabel("ID da Biblioteca:");
        JTextField txtId = new JTextField();

        JLabel lblNome = new JLabel("Novo Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblEndereco = new JLabel("Novo Endereço:");
        JTextField txtEndereco = new JTextField();

        JLabel lblTelefone = new JLabel("Novo Telefone:");
        JTextField txtTelefone = new JTextField();

        JButton btnBuscar = new JButton("Buscar");

        btnBuscar.addActionListener(e -> {

            try {

                int id = Integer.parseInt(
                        txtId.getText().trim());

                Biblioteca biblioteca = BibliotecaControle.obterBiblioteca(id);

                if (biblioteca == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Biblioteca não encontrada.");

                    return;
                }

                txtNome.setText(
                        biblioteca.getNomeBiblioteca());

                txtEndereco.setText(
                        biblioteca.getEndereco());

                txtTelefone.setText(
                        biblioteca.getTelefone());

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite um ID válido.");
            }
        });

        JButton btnSalvar = new JButton("Salvar Alteração");

        btnSalvar.addActionListener(e -> {

            try {

                int id = Integer.parseInt(
                        txtId.getText().trim());

                Biblioteca biblioteca = BibliotecaControle.obterBiblioteca(id);

                if (biblioteca == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Biblioteca não encontrada.");

                    return;
                }

                BibliotecaControle.editarBiblioteca(
                        id,
                        txtNome.getText().trim(),
                        txtEndereco.getText().trim(),
                        txtTelefone.getText().trim(),
                        this);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite um ID válido.");
            }
        });

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> mostrarCadastro());

        painel.add(lblId);
        painel.add(txtId);

        painel.add(lblNome);
        painel.add(txtNome);

        painel.add(lblEndereco);
        painel.add(txtEndereco);

        painel.add(lblTelefone);
        painel.add(txtTelefone);

        painel.add(btnBuscar);
        painel.add(btnSalvar);

        painel.add(new JLabel());
        painel.add(btnVoltar);

        setContentPane(painel);

        revalidate();
        repaint();
    }

    private void mostrarExclusao() {

        JPanel painel = new JPanel(
                new BorderLayout(10, 10));

        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel(
                "Excluir Biblioteca",
                SwingConstants.CENTER);

        titulo.setFont(
                new Font("Arial", Font.BOLD, 18));

        JPanel painelCentro = new JPanel(
                new GridLayout(2, 2, 10, 10));

        JLabel lblId = new JLabel("ID da Biblioteca:");

        JTextField txtId = new JTextField();

        JButton btnExcluir = new JButton("Excluir");

        btnExcluir.addActionListener(e -> {

            try {

                int id = Integer.parseInt(
                        txtId.getText().trim());

                Biblioteca biblioteca = BibliotecaControle.obterBiblioteca(id);

                if (biblioteca == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Biblioteca não encontrada.");

                    return;
                }

                BibliotecaControle.excluirBiblioteca(
                        id,
                        this);

                txtId.setText("");

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite um ID válido.");
            }
        });

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> mostrarCadastro());

        painelCentro.add(lblId);
        painelCentro.add(txtId);
        painelCentro.add(btnExcluir);
        painelCentro.add(btnVoltar);

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(painelCentro, BorderLayout.CENTER);

        setContentPane(painel);

        revalidate();
        repaint();
    }

    private void voltarMenu() {

        dispose();

        if (idSecretaria == 0) {

            new view.menus.MenuBiblioteca();

        } else {

            new view.menus.MenuSecretaria(idSecretaria);
        }
    }
}
