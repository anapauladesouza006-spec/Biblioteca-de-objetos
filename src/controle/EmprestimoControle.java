package controle;

import modelo.Emprestimo;
import util.manipuladorArquivos;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EmprestimoControle {
    public static void cadastrarEmprestimo(String data_devolucao_str, int id_livro, int id_secretaria, int id_leitor, JFrame tela, int idSecretaria){
        if(data_devolucao_str.isEmpty() || id_livro == 0 || id_secretaria == 0 || id_leitor == 0){
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataDevolucao;
        try{
            dataDevolucao = sdf.parse(data_devolucao_str);
            if(dataDevolucao.before(new Date())){
                JOptionPane.showMessageDialog(tela, "Data de devolução antiga informada!");
                return;
            }
        } catch(ParseException err){
            JOptionPane.showMessageDialog(tela, "Data inválida! Use dd/MM/yyyy");
            return;
        }

        int id = manipuladorArquivos.proximoId("Emprestimo");
        Emprestimo e = new Emprestimo(id, dataDevolucao, id_livro, id_secretaria, id_leitor);
        SecretariaControle.obterSecretaria(idSecretaria).cadastrarEmprestimo(e);

        JOptionPane.showMessageDialog(tela, "Paciente cadastrado com sucesso!");
        tela.dispose();

        new view.menus.MenuSecretaria(idSecretaria);

        JOptionPane.showMessageDialog(null, "Secretaria cadastrada com sucesso!");
        tela.dispose();
        new view.menus.MenuBiblioteca();
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
