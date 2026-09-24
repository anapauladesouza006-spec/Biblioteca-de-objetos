package controle;

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
}

