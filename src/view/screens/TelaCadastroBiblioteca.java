
package view.screens;

import controle.BibliotecaControle;

import javax.swing.*;
import java.awt.*;

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

        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> voltarMenu());

        painel.add(lblNome);
        painel.add(txtNome);

        painel.add(lblEndereco);
        painel.add(txtEndereco);

        painel.add(lblTelefone);
        painel.add(txtTelefone);

        painel.add(btnSalvar);

        painel.add(new JLabel());
        painel.add(btnVoltar);

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
