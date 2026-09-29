package controle;

import modelo.Livro;
import util.manipuladorArquivos;

import javax.swing.*;
import java.util.List;

public class LivroControle {

    // CADASTRAR
    public static void cadastrarLivro(
            String titulo,
            String autor,
            String genero,
            String status,
            JFrame tela,
            int idSecretaria) {

        if (titulo.isEmpty() ||
                autor.isEmpty() ||
                genero.isEmpty() ||
                status.isEmpty()) {

            JOptionPane.showMessageDialog(
                    tela,
                    "Preencha todos os campos.");

            return;
        }

        if (idSecretaria == 0 ||
                SecretariaControle.obterSecretaria(idSecretaria) == null) {

            JOptionPane.showMessageDialog(
                    tela,
                    "Secretaria não encontrada.");

            return;
        }

        int id = manipuladorArquivos.proximoId("Livro");

        Livro livro = new Livro(
                id,
                titulo,
                autor,
                genero,
                status);

        SecretariaControle.obterSecretaria(idSecretaria)
                .cadastrarLivro(livro);

        JOptionPane.showMessageDialog(
                tela,
                "Livro cadastrado com sucesso!");

        tela.dispose();

        new view.menus.MenuSecretaria(idSecretaria);
    }

    // LISTAR
    public static List<Livro> listarLivros() {
        return manipuladorArquivos.lerLivros();
    }

    // BUSCAR POR ID
    public static Livro obterLivro(int idLivro) {

        List<Livro> livros = manipuladorArquivos.lerLivros();

        return livros.stream()
                .filter(l -> l.getId_livro() == idLivro)
                .findFirst()
                .orElse(null);
    }

    // ALTERAR
    public static void editarLivro(
            int id,
            String titulo,
            String autor,
            String genero,
            String status,
            JFrame tela) {

        if (titulo.isEmpty() ||
                autor.isEmpty() ||
                genero.isEmpty() ||
                status.isEmpty()) {

            JOptionPane.showMessageDialog(
                    tela,
                    "Preencha todos os campos.");

            return;
        }

        Livro livro = new Livro(
                id,
                titulo,
                autor,
                genero,
                status);

        manipuladorArquivos.atualizarObjeto(
                "Livro",
                id,
                livro,
                5);

        JOptionPane.showMessageDialog(
                tela,
                "Livro atualizado com sucesso!");
    }

    // EXCLUIR
    public static void excluirLivro(
            int id,
            JFrame tela) {

        int resposta = JOptionPane.showConfirmDialog(
                tela,
                "Deseja realmente excluir este livro?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {

            manipuladorArquivos.excluirObjeto(
                    "Livro",
                    id);

            JOptionPane.showMessageDialog(
                    tela,
                    "Livro excluído com sucesso!");
        }
    }

    // LIVROS EMPRESTADOS
    public static List<Livro> livrosEmprestados() {

        List<Livro> livros =
                manipuladorArquivos.lerLivros();

        return livros.stream()
                .filter(l -> l.getStatus().equals("Emprestado"))
                .toList();
    }
}

