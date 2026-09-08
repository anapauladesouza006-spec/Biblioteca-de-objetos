package view.menus;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import util.manipuladorArquivos;

public class MenuInicial extends JFrame {

    public MenuInicial() {
        setTitle("Seleção de Perfil");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] perfis = {"Leitor", "Secretaria"};

        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lbl = new JLabel("Selecione o perfil de acesso:");
        JComboBox<String> comboPerfil = new JComboBox<>(perfis);
        JButton btnEntrar = new JButton("Entrar");

        btnEntrar.addActionListener(e -> {
            String perfilSelecionado = (String) comboPerfil.getSelectedItem();
            dispose();

            if ("Leitor".equals(perfilSelecionado)) {
                new MenuLeitor();
            } else {
                selecionarUsuario(perfilSelecionado);
            }
        });

        painel.add(lbl);
        painel.add(comboPerfil);
        painel.add(btnEntrar);
        painel.add(new JButton("Sair") {{
            addActionListener(e -> dispose());
        }});

        add(painel);
        setVisible(true);
    }

    private void selecionarUsuario(String perfil) {
        String arquivo = switch (perfil) {
            case "Leitor" -> "Leitor";
            case "Secretaria" -> "Secretaria";
            default -> null;
        };

        if (arquivo == null) {
            JOptionPane.showMessageDialog(null, "Perfil inválido.");
            new MenuInicial();
            return;
        }

        List<String[]> registros = manipuladorArquivos.ler(arquivo, getCamposEsperados(arquivo));
        if (registros.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado para " + perfil + ".");
            new MenuInicial();
            return;
        }

        String[] opcoes = registros.stream()
                .map(r -> r[1] + " (ID: " + r[0] + ")")
                .toArray(String[]::new);

        String escolha = (String) JOptionPane.showInputDialog(
                null,
                "Selecione o(a) " + perfil + ":",
                "Login",
                JOptionPane.PLAIN_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (escolha == null) {
            new MenuInicial();
            return;
        }

        int id = Integer.parseInt(escolha.split("ID: ")[1].replace(")", ""));
        switch (perfil) {
            case "Leitor" -> new MenuLeitor();
            case "Secretaria" -> new MenuSecretaria(id);
        }
    }

    private int getCamposEsperados(String nomeClasse) {
        return switch (nomeClasse) {
            case "Leitor" -> 4;
            case "Secretaria" -> 5;
            default -> 0;
        };
    }
}

