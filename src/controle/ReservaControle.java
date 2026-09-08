package controle;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReservaControle {
    public static void cadastrarPaciente(String nome, String dataNascimentoStr, String telefone, String email, JFrame tela, int idSecretaria){
        if(nome.isEmpty() || dataNascimentoStr.isEmpty() || telefone.isEmpty() || email.isEmpty()){
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }

        if(!email.contains("@")){
            JOptionPane.showMessageDialog(tela, "E-mail inválido!");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento;
        try{
            dataNascimento = sdf.parse(dataNascimentoStr);
            if(dataNascimento.after(new Date())){
                JOptionPane.showMessageDialog(tela, "Data de nascimento futura informada!");
                return;
            }
        } catch(ParseException err){
            JOptionPane.showMessageDialog(tela, "Data inválida! Use dd/MM/yyyy");
            return;
        }

        int id = ManipuladorArquivos.proximoId("Paciente");
        Paciente p = new Paciente(id, nome, dataNascimento, telefone, email);
        SecretariaControle.obterSecretaria(idSecretaria).cadastrarPaciente(p);

        JOptionPane.showMessageDialog(tela, "Paciente cadastrado com sucesso!");
        tela.dispose();

        new view.menus.MenuSecretaria(idSecretaria);

        JOptionPane.showMessageDialog(null, "Secretaria cadastrada com sucesso!");
        tela.dispose();
        new view.menus.MenuClinica();
    }

    public static Paciente obterPaciente(int idPaciente){
        List<Paciente> pacientes = ManipuladorArquivos.lerPacientes();
        Paciente paciente = pacientes.stream()
                .filter(p -> p.getIdPaciente() == idPaciente)
                .findFirst()
                .orElse(null);
        return paciente;
    }
}
