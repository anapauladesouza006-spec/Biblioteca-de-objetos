package controle;

import modelo.Emprestimo;
import util.manipuladorArquivos;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EmprestimoControle {
    public static void cadastrarEmprestimo(int id_livro, int id_secretaria, int id_leitor, JFrame tela){
        if(id_livro == 0 || id_secretaria == 0 || id_leitor == 0){
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataHoje = new Date();
        sdf.format(dataHoje);
        SimpleDateFormat nomeDia = new SimpleDateFormat("EEEE");

        Calendar cal = Calendar.getInstance();
        cal.setTime(dataHoje);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date dataDevolucao = cal.getTime();
        if(nomeDia.format(dataDevolucao).equals("domingo")){
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        else if(nomeDia.format(dataDevolucao).equals("sábado")){
            cal.add(Calendar.DAY_OF_MONTH, 2);
        }
        dataDevolucao = cal.getTime();
        System.out.println(dataDevolucao + nomeDia.format(dataDevolucao));


        int id = manipuladorArquivos.proximoId("Emprestimo");
        Emprestimo e = new Emprestimo( id, dataDevolucao, LivroControle.obterLivro(id_livro),SecretariaControle.obterSecretaria(id_secretaria), LeitorControle.obterLeitor(id_leitor));
        SecretariaControle.obterSecretaria(id_secretaria).cadastrarEmprestimo(e);

        JOptionPane.showMessageDialog(tela, "Empréstimo realizado com sucesso!");
        tela.dispose();

        new view.menus.MenuSecretaria(id_secretaria);
    }

    public static Emprestimo obterEmprestimo(int idEmprestimo){
        List<Emprestimo> emprestimos = manipuladorArquivos.lerEmprestimos();
        Emprestimo emprestimo = emprestimos.stream()
                .filter(e -> e.getId_emprestimo() == idEmprestimo)
                .findFirst()
                .orElse(null);
        return emprestimo;
    }
}
