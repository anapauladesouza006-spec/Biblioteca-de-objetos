package view.menus;

import controle.LeitorControle;
import controle.ReservaControle;
import modelo.Leitor;
import modelo.Livro;
import modelo.Reserva;
import util.manipuladorArquivos;
import view.screens.TelaCadastroLeitor;
import view.screens.TelaDevolucao;
import view.screens.TelaReserva;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class MenuLeitor extends JFrame {
    private int id_leitor;

    public MenuLeitor(int id_leitor) {
        this.id_leitor = id_leitor;

        setTitle("Menu Leitor - ID: " + this.id_leitor);
        setSize(450, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(10, 1, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        
        JButton btnListarLivros = new JButton("Listar Livros Disponíveis");
        btnListarLivros.addActionListener(e -> mostrarListaLivros());

        JButton btnCadastrarReserva = new JButton("Cadastrar Reserva");
        btnCadastrarReserva.addActionListener(e -> {
            dispose();
            new TelaReserva(0, this.id_leitor);
        });

        JButton btnListarReserva = new JButton("Listar Reservas");
        btnListarReserva.addActionListener(e -> mostrarListaReservas());

        JButton btnEditarReserva = new JButton("Editar Reserva");
        btnEditarReserva.addActionListener(e -> mostrarAlteracaoReserva());

        JButton btnExcluirReserva = new JButton("Excluir Reserva");
        btnExcluirReserva.addActionListener(e -> mostrarExclusaoReserva());

        JButton btnDevolver = new JButton("Realizar Devolução");
        btnDevolver.addActionListener(e -> {
            dispose();
            new TelaDevolucao(0, this.id_leitor);
        });

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> {
            dispose();
            new MenuInicial();
        });

        painel.add(btnListarLivros);
        painel.add(btnCadastrarReserva);
        painel.add(btnListarReserva);
        painel.add(btnEditarReserva);
        painel.add(btnExcluirReserva);
        painel.add(btnDevolver);
        painel.add(btnSair);

        add(painel);
        setVisible(true);
    }

    // CONSULTA DE LIVROS

    private void mostrarListaLivros() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Livros disponíveis", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JTextArea areaLista = new JTextArea();
        areaLista.setEditable(false);

        List<Livro> livros = manipuladorArquivos.lerLivros();

        if (livros.isEmpty()) {
            areaLista.setText("Nenhum livro disponível.");
        } else {
            StringBuilder texto = new StringBuilder();
            for (Livro livro : livros) {
                texto.append("ID: ").append(livro.getId_livro()).append("\n");
                texto.append("Título: ").append(livro.getTitulo()).append("\n");
                texto.append("Escrito por ").append(livro.getAutor()).append("\n");
                texto.append("Gênero: ").append(livro.getGenero()).append("\n");
                texto.append("Status de reserva: ").append(livro.getStatus()).append("\n");
                texto.append("-----------------------------\n");
            }
            areaLista.setText(texto.toString());
        }

        JScrollPane scroll = new JScrollPane(areaLista);
        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {
            new MenuLeitor(id_leitor);
            dispose();
        });

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);
        painel.add(btnVoltar, BorderLayout.SOUTH);

        setContentPane(painel);
        revalidate();
        repaint();
    }

    // CRUD DE RESERVA

    private void mostrarListaReservas() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Reservas Cadastradas", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JTextArea areaLista = new JTextArea();
        areaLista.setEditable(false);

        List<Reserva> reservas = ReservaControle.listarReservasLeitor(this.id_leitor);

        if (reservas.isEmpty()) {
            areaLista.setText("Nenhuma reserva cadastrada.");
        } else {
            StringBuilder texto = new StringBuilder();

            for (Reserva reserva : reservas) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                texto.append("ID Reserva: ").append(reserva.getid_reserva()).append("\n");
                texto.append("ID Leitor: ").append(reserva.getLeitor()).append("\n");
                texto.append("ID Livro: ").append(reserva.getLivro()).append("\n");
                texto.append("Data da Reserva: ").append(sdf.format(reserva.getData_retirada())).append("\n");
                texto.append("-----------------------------\n");
            }

            areaLista.setText(texto.toString());
        }

        JScrollPane scroll = new JScrollPane(areaLista);
        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {
            new MenuLeitor(id_leitor);
            dispose();
        });

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);
        painel.add(btnVoltar, BorderLayout.SOUTH);

        setContentPane(painel);
        revalidate();
        repaint();
    }

    private void mostrarAlteracaoReserva() {
        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblReserva = new JLabel("Reserva:");
        List<Reserva> reservas = ReservaControle.listarReservasLeitor(this.id_leitor);
        JComboBox<Reserva> cmbReserva = new JComboBox<>();

        for (Reserva r : reservas) {
            cmbReserva.addItem(r);
        }

        JLabel lblNovaData = new JLabel("Nova Data (DD/MM/AAAA):");
        JTextField txtNovaData = new JTextField();

        JButton btnBuscar = new JButton("Buscar");

        btnBuscar.addActionListener(e -> {
            try {
                Reserva reserva = (Reserva) cmbReserva.getSelectedItem();
                if (reserva == null) {
                    JOptionPane.showMessageDialog(this, "Reserva não encontrada.");
                    return;
                }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                txtNovaData.setText((sdf.format(reserva.getData_retirada())));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao carregar os dados da reserva.");
            }
        });

        JButton btnSalvar = new JButton("Salvar Alteração");

        btnSalvar.addActionListener(e -> {
            try {
                if (cmbReserva.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(this, "Cadastre uma reserva para realizar alterações.");
                    return;
                }

                Reserva reserva = (Reserva) cmbReserva.getSelectedItem();

                ReservaControle.editarReserva(
                        reserva.getid_reserva(),
                        txtNovaData.getText().trim(),
                        this);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao alterar a reserva.");
            }
        });

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuLeitor(id_leitor);
        });

        painel.add(lblReserva);
        painel.add(cmbReserva);
        painel.add(lblNovaData);
        painel.add(txtNovaData);
        painel.add(btnBuscar);
        painel.add(btnSalvar);
        painel.add(new JLabel());
        painel.add(btnVoltar);

        setContentPane(painel);
        revalidate();
        repaint();
    }

    private void mostrarExclusaoReserva() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Excluir Reserva", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel painelCentro = new JPanel(new GridLayout(2, 2, 10, 10));
        JLabel lblId = new JLabel("ID da Reserva:");

        List<Reserva> reservas = manipuladorArquivos.lerReservas();
        JComboBox<Reserva> cmbReserva = new JComboBox<>();

        for (Reserva r : reservas) {
            cmbReserva.addItem(r);
        }

        JButton btnExcluir = new JButton("Excluir");

        btnExcluir.addActionListener(e -> {
            try {
                Reserva reserva = (Reserva) cmbReserva.getSelectedItem();

                if (reserva == null) {
                    JOptionPane.showMessageDialog(this, "Reserva não encontrada.");
                    return;
                }

                ReservaControle.excluirReserva(reserva.getid_reserva(), this);

                cmbReserva.removeAllItems();
                List<Reserva> reservasNovas = manipuladorArquivos.lerReservas();

                for (Reserva r : reservasNovas) {
                    cmbReserva.addItem(r);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir reserva.");
            }
        });

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> {
            dispose();
            new MenuLeitor(id_leitor);
        });

        painelCentro.add(lblId);
        painelCentro.add(cmbReserva);
        painelCentro.add(btnExcluir);
        painelCentro.add(btnVoltar);

        painel.add(titulo, BorderLayout.NORTH);
        painel.add(painelCentro, BorderLayout.CENTER);

        setContentPane(painel);
        revalidate();
        repaint();
    }
}