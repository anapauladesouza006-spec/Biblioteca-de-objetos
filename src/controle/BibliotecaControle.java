package controle;

import util.manipuladorArquivos;
import modelo.Biblioteca;
import javax.swing.*;
import java.util.List;

public class BibliotecaControle {
    public static void cadastrarBiblioteca(String nome, String endereco, String telefone, JFrame tela) {
        if (nome.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos.");
            return;
        }

        int id = manipuladorArquivos.proximoId("Biblioteca");
        Biblioteca c = new Biblioteca(id, nome, endereco, telefone);
        manipuladorArquivos.salvar("Biblioteca", c.toCSV(),4);

        JOptionPane.showMessageDialog(tela, "Biblioteca cadastrada com sucesso!");
        tela.dispose();
        new view.menus.MenuBiblioteca();
    }

    public static Biblioteca obterBiblioteca() {
        List<Biblioteca> lista = manipuladorArquivos.lerBibliotecas();
        return lista.isEmpty() ? null : lista.get(0);
    }
}
