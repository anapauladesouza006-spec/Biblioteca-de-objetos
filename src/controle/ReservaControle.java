package controle;

import modelo.Leitor;
import modelo.Livro;
import modelo.Reserva;
import util.manipuladorArquivos;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReservaControle {
    public static void cadastrarReserva(String dataRetirada, int idLivro, int idLeitor, String status, JFrame tela,
            int idSecretaria) {
        if (dataRetirada.isEmpty() || idLivro == 0 || idLeitor == 0 || status.isEmpty()) {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        Date data;

        try {
            data = sdf.parse(dataRetirada);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(tela, "Informe uma data válida no formato dd/MM/yyyy.");
            return;
        }

        Livro livro = LivroControle.obterLivro(idLivro);
        Leitor leitor = LeitorControle.obterLeitor(idLeitor);

        if (livro == null) {
            JOptionPane.showMessageDialog(tela, "Livro não encontrado.");
            return;
        }

        if (leitor == null) {
            JOptionPane.showMessageDialog(tela, "Leitor não encontrado.");
            return;
        }

        if (SecretariaControle.obterSecretaria(idSecretaria) == null) {
            JOptionPane.showMessageDialog(tela, "Secretaria não encontrada.");
            return;
        }

        int id = manipuladorArquivos.proximoId("Reserva");

        Reserva reserva = new Reserva(id, status, data, livro, leitor);

        SecretariaControle.obterSecretaria(idSecretaria).cadastrarReserva(reserva);

        JOptionPane.showMessageDialog(tela, "Reserva realizada com sucesso!");

        tela.dispose();

        new view.menus.MenuSecretaria(idSecretaria);
    }

    public static List<Reserva> listarReservas() {
        return manipuladorArquivos.lerReservas();
    }
}
