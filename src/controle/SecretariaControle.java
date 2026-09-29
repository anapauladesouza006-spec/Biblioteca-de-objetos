package controle;

import modelo.Biblioteca;
import modelo.Secretaria;
import util.manipuladorArquivos;

import javax.swing.*;
import java.util.List;

public class SecretariaControle {

    public static void cadastrarSecretaria(String nome, String cargo, String telefone, String email, JFrame tela) {
        if (nome.isEmpty() || cargo.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }

        if (BibliotecaControle.obterBiblioteca() == null) {
            JOptionPane.showMessageDialog(tela, "Cadastre uma biblioteca antes de cadastrar a secretaria.");
            return;
        }

        int id = manipuladorArquivos.proximoId("Secretaria");

        Secretaria secretaria = new Secretaria(id, nome, cargo, telefone, email);

        BibliotecaControle.obterBiblioteca().cadastrarSecretaria(secretaria);

        JOptionPane.showMessageDialog(tela, "Secretaria cadastrada com sucesso!");

        tela.dispose();

        new view.menus.MenuBiblioteca();
    }

    public static Secretaria obterSecretaria(int idSecretaria) {
        List<Secretaria> secretarias = manipuladorArquivos.lerSecretarias();

        return secretarias.stream()
                .filter(s -> s.getId_secretaria() == idSecretaria)
                .findFirst()
                .orElse(null);
    }

    public static Secretaria obterSecretarias() {

        List<Secretaria> lista = manipuladorArquivos.lerSecretarias();

        return lista.isEmpty()
                ? null
                : lista.get(0);
    }

    public static void editarSecretaria(
            int id,
            String nome,
            String cargo,
            String email,
            String telefone,
            JFrame tela) {

        if (nome.isEmpty() ||
                cargo.isEmpty() ||
                email.isEmpty() ||
                telefone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    tela,
                    "Preencha todos os campos.");

            return;
        }

        Secretaria secretaria = new Secretaria(
                id,
                nome,
                cargo,
                email,
                telefone);

        manipuladorArquivos.atualizarObjeto(
                "Secretaria",
                id,
                secretaria,
                5);

        JOptionPane.showMessageDialog(
                tela,
                "Secretária atualizada com sucesso!");
    }

    public static void excluirSecretaria(
            int id,
            JFrame tela) {

        int resposta = JOptionPane.showConfirmDialog(
                tela,
                "Deseja realmente excluir esta secretária?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {

            manipuladorArquivos.excluirObjeto(
                    "Secretaria",
                    id);

            JOptionPane.showMessageDialog(
                    tela,
                    "Secretaria excluída com sucesso!");
        }
    }
}

