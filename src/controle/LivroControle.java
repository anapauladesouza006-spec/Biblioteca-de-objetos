package controle;

import modelo.Livro;
import util.manipuladorArquivos;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LivroControle {
    public static void cadastrarLivro(String titulo, String autor, String genero, String status, JFrame tela, int idSecretaria){
        if(titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || status.isEmpty()){
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }

        int id = manipuladorArquivos.proximoId("Livro");
        Livro l = new Livro(id, titulo, autor, genero, status);
        SecretariaControle.obterSecretaria(idSecretaria).cadastrarLivro(l);

        JOptionPane.showMessageDialog(tela, "Livro cadastrado com sucesso!");
        tela.dispose();

        new view.menus.MenuSecretaria(idSecretaria);

        JOptionPane.showMessageDialog(null, "Secretaria cadastrada com sucesso!");
        tela.dispose();
        new view.menus.MenuBiblioteca();
    }

    public static Livro obterLivro(int idLivro){
        List<Livro> livros = manipuladorArquivos.lerLivros();
        Livro livro = livros.stream()
                .filter(l -> l.getId_livro() == idLivro)
                .findFirst()
                .orElse(null);
        return livro;
    }
    }