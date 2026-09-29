package view.menus;

import javax.swing.*;

import controle.BibliotecaControle;
import controle.SecretariaControle;
import modelo.Biblioteca;
import modelo.Secretaria;
import view.screens.*;
import util.manipuladorArquivos;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class MenuBiblioteca extends JFrame {

    public MenuBiblioteca() {
        setTitle("Menu Biblioteca");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnSecretaria = new JButton("Cadastrar Secretaria");
        btnSecretaria.addActionListener(e -> {
            dispose();
            new TelaCadastroSecretaria(0);
        });

        JButton btnEditarSecretaria = new JButton("Editar Secretaria");
        btnEditarSecretaria.addActionListener(e -> {
            mostrarAlteracaoSecretaria();
        });

        JButton btnListarSecretaria = new JButton("Listar Secretarias");
        btnListarSecretaria.addActionListener(e -> {
        mostrarListaSecretaria();
        });

        JButton btnExcluirSecretaria = new JButton("Excluir Secretaria");

        btnExcluirSecretaria.addActionListener(e -> mostrarExclusaoSecretaria());

        JButton btnBiblioteca = new JButton("Cadastrar Biblioteca");
        btnBiblioteca.addActionListener(e -> {
            dispose();
            new TelaCadastroBiblioteca(0);
        });

        JButton btnListarBiblioteca = new JButton("Listar Bibliotecas");
        btnListarBiblioteca.addActionListener(e -> mostrarListaBiblioteca());

        JButton btnEditarBiblioteca = new JButton("Editar Biblioteca");

        btnEditarBiblioteca.addActionListener(e -> mostrarAlteracaoBiblioteca());

        JButton btnExcluirBiblioteca = new JButton("Excluir Biblioteca");

        btnExcluirBiblioteca.addActionListener(e -> mostrarExclusaoBiblioteca());

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });
        painel.add(btnSecretaria);
        painel.add(btnBiblioteca);
        painel.add(btnListarSecretaria);
        painel.add(btnListarBiblioteca);
        painel.add(btnEditarSecretaria);
        painel.add(btnEditarBiblioteca);
        painel.add(btnExcluirSecretaria);
        painel.add(btnExcluirBiblioteca);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }

    private void mostrarListaBiblioteca() {

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

        btnVoltar.addActionListener(e ->{
                new MenuBiblioteca();
                dispose();});

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);
        painel.add(btnVoltar, BorderLayout.SOUTH);

        setContentPane(painel);

        revalidate();
        repaint();
    }

    private void mostrarAlteracaoBiblioteca() {

        JPanel painel = new JPanel(
                new GridLayout(6, 2, 10, 10));

        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblBiblioteca = new JLabel("Biblioteca:");

        List<Biblioteca> bibliotecas = manipuladorArquivos.lerBibliotecas();

        JComboBox<Biblioteca> cmbBiblioteca = new JComboBox<>();

        for (Biblioteca b : bibliotecas) {
            cmbBiblioteca.addItem(b);
        }

        JLabel lblNome = new JLabel("Novo Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblEndereco = new JLabel("Novo Endereço:");
        JTextField txtEndereco = new JTextField();

        JLabel lblTelefone = new JLabel("Novo Telefone:");
        JTextField txtTelefone = new JTextField();

        JButton btnBuscar = new JButton("Buscar");

        btnBuscar.addActionListener(e -> {

            try {

                Biblioteca biblioteca = (Biblioteca) cmbBiblioteca.getSelectedItem();

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

            if (cmbBiblioteca.getSelectedItem() == null || cmbBiblioteca.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(
                        this,
                        "Cadastre uma biblioteca para realizar alterações"
                );
                return;
            }

            Biblioteca biblioteca = (Biblioteca) cmbBiblioteca.getSelectedItem();


                BibliotecaControle.editarBiblioteca(
                        biblioteca.getId_biblioteca(),
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

        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuBiblioteca();
        });

        painel.add(lblBiblioteca);
        painel.add(cmbBiblioteca);

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

    private void mostrarListaSecretaria() {

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        painel.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel(
                "Secretárias cadastradas",
                SwingConstants.CENTER);

        titulo.setFont(
                new Font("Arial", Font.BOLD, 18));

        JTextArea areaLista = new JTextArea();

        areaLista.setEditable(false);

        List<Secretaria> secretarias = manipuladorArquivos.lerSecretarias();

        if (secretarias.isEmpty()) {

            areaLista.setText(
                    "Nenhuma secretária cadastrada.");

        } else {

            StringBuilder texto = new StringBuilder();

            for (Secretaria secretaria : secretarias) {

                texto.append("ID: ")
                        .append(secretaria.getId_secretaria())
                        .append("\n");

                texto.append("Nome: ")
                        .append(secretaria.getNome())
                        .append("\n");

                texto.append("Cargo: ")
                        .append(secretaria.getCargo())
                        .append("\n");

                texto.append("E-mail: ")
                        .append(secretaria.getEmail())
                        .append("\n");

                texto.append("Telefone: ")
                        .append(secretaria.getTelefone())
                        .append("\n");

                texto.append("-----------------------------\n");
            }

            areaLista.setText(texto.toString());
        }

        JScrollPane scroll = new JScrollPane(areaLista);

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e ->{
            new MenuBiblioteca();
            dispose();});

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);
        painel.add(btnVoltar, BorderLayout.SOUTH);

        setContentPane(painel);

        revalidate();
        repaint();
    }

    private void mostrarAlteracaoSecretaria() {

        JPanel painel = new JPanel(
                new GridLayout(7, 2, 10, 10));

        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblSecretaria = new JLabel("Secretária:");

        List<Secretaria> secretarias = manipuladorArquivos.lerSecretarias();

        JComboBox<Secretaria> cmbSecretaria = new JComboBox<>();

        for (Secretaria s : secretarias) {
            cmbSecretaria.addItem(s);
        }

        JLabel lblNome = new JLabel("Novo Nome:");
        JTextField txtNome = new JTextField();

        JLabel lblCargo = new JLabel("Novo Cargo:");
        JTextField txtCargo = new JTextField();

        JLabel lblEmail = new JLabel("Novo E-mail:");
        JTextField txtEmail = new JTextField();

        JLabel lblTelefone = new JLabel("Novo Telefone:");
        JTextField txtTelefone = new JTextField();

        JButton btnBuscar = new JButton("Buscar");

        btnBuscar.addActionListener(e -> {

            try {

                Secretaria secretaria = (Secretaria) cmbSecretaria.getSelectedItem();

                if (secretaria == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Secretaria não encontrada.");

                    return;
                }

                txtNome.setText(
                        secretaria.getNome());

                txtCargo.setText(
                        secretaria.getCargo());

                txtEmail.setText(
                        secretaria.getEmail());

                txtTelefone.setText(
                        secretaria.getTelefone());

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite um ID válido.");
            }
        });

        JButton btnSalvar = new JButton("Salvar Alteração");

        btnSalvar.addActionListener(e -> {

            try {

                if (cmbSecretaria.getSelectedItem() == null || cmbSecretaria.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Cadastre uma secretária para realizar alterações"
                    );
                    return;
                }

                modelo.Secretaria secretaria = (Secretaria) cmbSecretaria.getSelectedItem();


                SecretariaControle.editarSecretaria(
                        secretaria.getId_secretaria(),
                        txtNome.getText().trim(),
                        txtCargo.getText().trim(),
                        txtEmail.getText().trim(),
                        txtTelefone.getText().trim(),
                        this);

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite um ID válido.");
            }
        });

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuBiblioteca();
        });

        painel.add(lblSecretaria);
        painel.add(cmbSecretaria);

        painel.add(lblNome);
        painel.add(txtNome);

        painel.add(lblCargo);
        painel.add(txtCargo);

        painel.add(lblEmail);
        painel.add(txtEmail);

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

    private void mostrarExclusaoBiblioteca() {

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

        List<Biblioteca> bibliotecas = manipuladorArquivos.lerBibliotecas();

        JComboBox<Biblioteca> cmbBiblioteca = new JComboBox<>();

        for (Biblioteca b : bibliotecas) {
            cmbBiblioteca.addItem(b);
        }

        JButton btnExcluir = new JButton("Excluir");

        btnExcluir.addActionListener(e -> {

            try {

                Biblioteca biblioteca = (Biblioteca) cmbBiblioteca.getSelectedItem();

                if (biblioteca == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Biblioteca não encontrada.");

                    return;
                }

                BibliotecaControle.excluirBiblioteca(
                        biblioteca.getId_biblioteca(),
                        this);

                //txtId.setText("");
                cmbBiblioteca.removeAllItems();
                List<Biblioteca> bibliotecasNovas = manipuladorArquivos.lerBibliotecas();

                for (Biblioteca b : bibliotecasNovas) {
                    cmbBiblioteca.addItem(b);
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite um ID válido.");
            }
        });

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuBiblioteca();
        });

        painelCentro.add(lblId);
        painelCentro.add(cmbBiblioteca);
        painelCentro.add(btnExcluir);
        painelCentro.add(btnVoltar);

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(painelCentro, BorderLayout.CENTER);

        setContentPane(painel);

        revalidate();
        repaint();
    }

    private void mostrarExclusaoSecretaria() {

        JPanel painel = new JPanel(
                new BorderLayout(10, 10));

        painel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel(
                "Excluir Secretaria",
                SwingConstants.CENTER);

        titulo.setFont(
                new Font("Arial", Font.BOLD, 18));

        JPanel painelCentro = new JPanel(
                new GridLayout(2, 2, 10, 10));

        JLabel lblId = new JLabel("ID da Secretaria:");

        List<Secretaria> secretarias = manipuladorArquivos.lerSecretarias();

        JComboBox<Secretaria> cmbSecretaria = new JComboBox<>();

        for (Secretaria s : secretarias) {
            cmbSecretaria.addItem(s);
        }

        JButton btnExcluir = new JButton("Excluir");

        btnExcluir.addActionListener(e -> {

            try {

                Secretaria secretaria = (Secretaria) cmbSecretaria.getSelectedItem();

                if (secretaria == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Secretária não encontrada.");

                    return;
                }

                SecretariaControle.excluirSecretaria(
                        secretaria.getId_secretaria(),
                        this);

                //txtId.setText("");
                cmbSecretaria.removeAllItems();
                List<Secretaria> secretariasNovas = manipuladorArquivos.lerSecretarias();

                for (Secretaria s : secretariasNovas) {
                    cmbSecretaria.addItem(s);
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite um ID válido.");
            }
        });

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuBiblioteca();
        });

        painelCentro.add(lblId);
        painelCentro.add(cmbSecretaria);
        painelCentro.add(btnExcluir);
        painelCentro.add(btnVoltar);

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(painelCentro, BorderLayout.CENTER);

        setContentPane(painel);

        revalidate();
        repaint();
    }
}
