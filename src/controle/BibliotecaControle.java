package controle;

import util.manipuladorArquivos;
import modelo.Biblioteca;

import javax.swing.*;
import java.util.List;

public class BibliotecaControle {

    public static void cadastrarBiblioteca(
            String nome,
            String endereco,
            String telefone,
            JFrame tela,
            int id_secretaria) {

        if (nome.isEmpty() ||
                endereco.isEmpty() ||
                telefone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    tela,
                    "Preencha todos os campos.");

            return;
        }

        int id = manipuladorArquivos.proximoId("Biblioteca");

        Biblioteca c = new Biblioteca(
                id,
                nome,
                endereco,
                telefone);

        manipuladorArquivos.salvar(
                "Biblioteca",
                c.toCSV(),
                4);

        JOptionPane.showMessageDialog(
                tela,
                "Biblioteca cadastrada com sucesso!");

        tela.dispose();

        if (id_secretaria == 0) {
            new view.menus.MenuBiblioteca();
        } else {
            new view.menus.MenuSecretaria(id_secretaria);
        }
    }

    public static List<Biblioteca> listarBibliotecas() {

        return manipuladorArquivos.lerBibliotecas();
    }

    // OBTER BIBLIOTECA PELO ID

    public static Biblioteca obterBiblioteca(int id) {

        List<Biblioteca> lista = manipuladorArquivos.lerBibliotecas();

        for (Biblioteca biblioteca : lista) {

            if (biblioteca.getId_biblioteca() == id) {
                return biblioteca;
            }
        }

        return null;
    }

    public static void editarBiblioteca(
            int id,
            String nome,
            String endereco,
            String telefone,
            JFrame tela) {

        if (nome.isEmpty() ||
                endereco.isEmpty() ||
                telefone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    tela,
                    "Preencha todos os campos.");

            return;
        }

        Biblioteca biblioteca = new Biblioteca(
                id,
                nome,
                endereco,
                telefone);

        manipuladorArquivos.atualizarObjeto(
                "Biblioteca",
                id,
                biblioteca,
                4);

        JOptionPane.showMessageDialog(
                tela,
                "Biblioteca atualizada com sucesso!");
    }

    public static void excluirBiblioteca(
            int id,
            JFrame tela) {

        int resposta = JOptionPane.showConfirmDialog(
                tela,
                "Deseja realmente excluir esta biblioteca?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {

            manipuladorArquivos.excluirObjeto(
                    "Biblioteca",
                    id);

            JOptionPane.showMessageDialog(
                    tela,
                    "Biblioteca excluída com sucesso!");
        }
    }

    public static Biblioteca obterBiblioteca() {

        List<Biblioteca> lista = manipuladorArquivos.lerBibliotecas();

        return lista.isEmpty()
                ? null
                : lista.get(0);
    }
}