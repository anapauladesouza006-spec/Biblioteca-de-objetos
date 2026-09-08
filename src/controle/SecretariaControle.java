package controle;

import modelo.Secretaria;
import util.manipuladorArquivos;

import javax.swing.*;
import java.util.List;

public class SecretariaControle {

    public static void cadastrarSecretaria(String nome, String cargo, String telefone, String email, JFrame tela){
        if(nome.isEmpty() || cargo.isEmpty() || telefone.isEmpty() || email.isEmpty()){
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }

        int id = manipuladorArquivos.proximoId("Secretaria");
        Secretaria s = new Secretaria(id, nome, cargo, telefone,email);
        BibliotecaControle.obterBiblioteca().cadastrarSecretaria(s);

        JOptionPane.showMessageDialog(tela, "Secretaria cadastrada com sucesso!");
        tela.dispose();
        new view.menus.MenuBiblioteca();
    }

    public static Secretaria obterSecretaria(int idSecretaria){
        List<Secretaria> secretarias = manipuladorArquivos.lerSecretarias();
        Secretaria secretaria = secretarias.stream()
                .filter(s -> s.getId_secretaria() == idSecretaria)
                .findFirst()
                .orElse(null);
        return secretaria;
    }
    }