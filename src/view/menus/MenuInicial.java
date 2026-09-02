package visao.menus;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import util.ManipuladorArquivos;

public class MenuInicial extends JFrame {

    public MenuInicial() {
        setTitle("Seleção de Perfil");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] perfis = {"Clínica", "Médico", "Secretaria", "Paciente"};

        JPanel painel = new JPanel(new GridLayout(4, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lbl = new JLabel("Selecione o perfil de acesso:");
        JComboBox<String> comboPerfil = new JComboBox<>(perfis);
        JButton btnEntrar = new JButton("Entrar");

        btnEntrar.addActionListener(e -> {
            String perfilSelecionado = (String) comboPerfil.getSelectedItem();
            dispose();

            if ("Clínica".equals(perfilSelecionado)) {
                new MenuClinica();
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
            case "Clínica" -> "Clinica";
            case "Médico" -> "Medico";
            case "Secretaria" -> "Secretaria";
            case "Paciente" -> "Paciente";
            default -> null;
        };

        if (arquivo == null) {
            JOptionPane.showMessageDialog(null, "Perfil inválido.");
            new MenuInicial();
            return;
        }

        List<String[]> registros = ManipuladorArquivos.ler(arquivo, getCamposEsperados(arquivo));
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
            case "Clínica" -> new MenuClinica();
            case "Médico" -> new MenuMedico(id);
            case "Secretaria" -> new MenuSecretaria(id);
            case "Paciente" -> new MenuPaciente(id);
        }
    }

    private int getCamposEsperados(String nomeClasse) {
        return switch (nomeClasse) {
            case "Paciente" -> 5;
            case "Medico" -> 4;
            case "Secretaria" -> 3;
            case "Clinica" -> 4;
            default -> 0;
        };
    }
}

