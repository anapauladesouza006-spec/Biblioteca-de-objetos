package controle;

import modelo.Leitor;
import util.manipuladorArquivos;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReservaControle {
    public static void cadastrarPaciente(String nome, String telefone, String cpf, JFrame tela, int idSecretaria){
        if(nome.isEmpty() || telefone.isEmpty() || cpf.isEmpty()){
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }

        int id = manipuladorArquivos.proximoId("Leitor");
        Leitor l = new Leitor(id, nome, telefone, cpf);
        SecretariaControle.obterSecretaria(idSecretaria).cadastrarLeitor(l);

        JOptionPane.showMessageDialog(tela, "Leitor cadastrado com sucesso!");
        tela.dispose();

        new view.menus.MenuSecretaria(idSecretaria);

        JOptionPane.showMessageDialog(null, "Secretaria cadastrada com sucesso!");
        tela.dispose();
        new view.menus.MenuBiblioteca();
    }

    public static Leitor obterLeitor(int idLeitor){
        List<Leitor> leitores = manipuladorArquivos.lerLeitores();
        Leitor leitor = leitores.stream()
                .filter(l -> l.getId_leitor() == idLeitor)
                .findFirst()
                .orElse(null);
        return leitor;
    }
}
