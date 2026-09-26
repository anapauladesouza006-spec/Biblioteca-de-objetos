package controle;

import modelo.Emprestimo;
import modelo.Livro;
import util.manipuladorArquivos;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmprestimoControle {
    public static void cadastrarEmprestimo(int idLivro, int idSecretaria, int idLeitor, JFrame tela) {
        if (idLivro == 0 || idSecretaria == 0 || idLeitor == 0) {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }

        Livro livro = LivroControle.obterLivro(idLivro);

        if (livro == null) {
            JOptionPane.showMessageDialog(tela, "Livro não encontrado.");
            return;
        }

        if (livro.getStatus().equalsIgnoreCase("Emprestado")) {
            JOptionPane.showMessageDialog(tela, "Este livro já está emprestado.");
            return;
        }

        if (SecretariaControle.obterSecretaria(idSecretaria) == null) {
            JOptionPane.showMessageDialog(tela, "Secretaria não encontrada.");
            return;
        }

        if (LeitorControle.obterLeitor(idLeitor) == null) {
            JOptionPane.showMessageDialog(tela, "Leitor não encontrado.");
            return;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 7);

        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 2);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }

        Date dataDevolucao = cal.getTime();
        int id = manipuladorArquivos.proximoId("Emprestimo");

        Emprestimo emprestimo = new Emprestimo(
                id,
                dataDevolucao,
                livro,
                SecretariaControle.obterSecretaria(idSecretaria),
                LeitorControle.obterLeitor(idLeitor)
        );

        SecretariaControle.obterSecretaria(idSecretaria).cadastrarEmprestimo(emprestimo);

        livro.setStatus("Emprestado");
        manipuladorArquivos.atualizarObjeto("Livro", livro.getId_livro(), livro, 5);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        JOptionPane.showMessageDialog(
                tela,
                "Empréstimo realizado com sucesso!\nData de devolução: " + sdf.format(dataDevolucao)
        );

        tela.dispose();
        new view.menus.MenuSecretaria(idSecretaria);
    }

    public static Emprestimo obterEmprestimo(int idEmprestimo) {
        List<Emprestimo> emprestimos = manipuladorArquivos.lerEmprestimos();

        return emprestimos.stream()
                .filter(e -> e.getId_emprestimo() == idEmprestimo)
                .findFirst()
                .orElse(null);
    }

    public static Emprestimo buscarEmprestimo(int id_leitor, int id_livro) {
        List<Emprestimo> emprestimos = manipuladorArquivos.lerEmprestimos();

        return emprestimos.stream()
                .filter(e -> e.getLeitor().getId_leitor() == id_leitor)
                .filter(e -> e.getLivro().getId_livro() == id_livro)
                .findFirst()
                .orElse(null);
    }
}
