package controle;

import modelo.Leitor;
import util.manipuladorArquivos;

import javax.swing.*;
import java.util.List;

public class LeitorControle {
    public static void cadastrarLeitor(String nome, String telefone, String cpf, JFrame tela, int idSecretaria) {
        if (nome.isEmpty() || telefone.isEmpty() || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }

        if (idSecretaria == 0 || SecretariaControle.obterSecretaria(idSecretaria) == null) {
            JOptionPane.showMessageDialog(tela, "Secretaria não encontrada.");
            return;
        }

        int id = manipuladorArquivos.proximoId("Leitor");

        Leitor leitor = new Leitor(id, nome, telefone, cpf);

        SecretariaControle.obterSecretaria(idSecretaria).cadastrarLeitor(leitor);

        JOptionPane.showMessageDialog(tela, "Leitor cadastrado com sucesso!");

        tela.dispose();

        new view.menus.MenuSecretaria(idSecretaria);
    }

    public static Leitor obterLeitor(int idLeitor) {
        List<Leitor> leitores = manipuladorArquivos.lerLeitores();

        return leitores.stream()
                .filter(l -> l.getId_leitor() == idLeitor)
                .findFirst()
                .orElse(null);
    }
}

