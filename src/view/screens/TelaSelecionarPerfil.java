package visao.telas;

import javax.swing.*;
import java.util.List;

public class TelaSelecionarPerfil {
    public static void exibir() {
        String[] perfis = { "Clínica", "Médico", "Secretaria", "Paciente" };
        String escolha = (String) JOptionPane.showInputDialog(null,
                "Selecione o perfil de acesso:", "Login", JOptionPane.QUESTION_MESSAGE,
                null, perfis, perfis[0]);

        if (escolha == null) {
            JOptionPane.showMessageDialog(null, "Acesso cancelado.");
            System.exit(0);
        }

        if (!escolha.equals("Clínica")) {
            selecionarEntidade(escolha);
        }
    }

    private static void selecionarEntidade(String tipo) {
        String nomeArquivo = tipo;
        int campos = switch (tipo) {
            case "Paciente" -> 5;
            case "Medico" -> 4;
            case "Secretaria" -> 3;
            case "Clínica" -> 4;
            default -> 0;
        };

        List<String[]> dados = util.manipuladorArquivos.ler(nomeArquivo, campos);
        if (dados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado para " + tipo);
            System.exit(0);
        }

        String[] opcoes = dados.stream()
                .map(l -> l[1] + " (ID: " + l[0] + ")")
                .toArray(String[]::new);

        String escolha = (String) JOptionPane.showInputDialog(null,
                "Selecione o(a) " + tipo.toLowerCase() + ":",
                "Login " + tipo, JOptionPane.PLAIN_MESSAGE,
                null, opcoes, opcoes[0]);

        if (escolha == null) {
            JOptionPane.showMessageDialog(null, "Acesso cancelado.");
            System.exit(0);
        }

    }
}