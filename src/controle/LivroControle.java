package controle;

import modelo.Livro;
import util.manipuladorArquivos;

import javax.swing.*;
import java.util.List;

public class LivroControle {
    public static void cadastrarLivro(String titulo, String autor, String genero, String status, JFrame tela, int idSecretaria) {
        if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }

        if (idSecretaria == 0 || SecretariaControle.obterSecretaria(idSecretaria) == null) {
            JOptionPane.showMessageDialog(tela, "Secretaria não encontrada.");
            return;
        }

        int id = manipuladorArquivos.proximoId("Livro");

        Livro livro = new Livro(id, titulo, autor, genero, status);

        SecretariaControle.obterSecretaria(idSecretaria).cadastrarLivro(livro);

        JOptionPane.showMessageDialog(tela, "Livro cadastrado com sucesso!");

        tela.dispose();

        new view.menus.MenuSecretaria(idSecretaria);
    }

    public static Livro obterLivro(int idLivro) {
        List<Livro> livros = manipuladorArquivos.lerLivros();

        return livros.stream()
                .filter(l -> l.getId_livro() == idLivro)
                .findFirst()
                .orElse(null);
    }
}
