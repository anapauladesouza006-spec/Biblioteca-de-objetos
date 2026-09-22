package controle;

import modelo.*;
import util.manipuladorArquivos;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReservaControle {
    public static void cadastrarReserva(String data_retirada, int id_livro, int id_leitor, String status, JFrame tela,
            int id_secretaria) {
        if (data_retirada.isEmpty() || id_livro == 0 || id_leitor == 0) {
            JOptionPane.showMessageDialog(tela, "Preencha todos os campos");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = sdf.parse(data_retirada);
        int id = manipuladorArquivos.proximoId("Leitor");
        Reserva r = new Reserva(id, status, data, LivroControle.obterLivro(id_livro),
                LeitorControle.obterLeitor(id_leitor));
        SecretariaControle.obterSecretaria(id_secretaria).cadastrarReserva(r);

        JOptionPane.showMessageDialog(tela, "Leitor cadastrado com sucesso!");
        tela.dispose();

        new view.menus.MenuSecretaria(id_secretaria);

        JOptionPane.showMessageDialog(null, "Secretaria cadastrada com sucesso!");
        tela.dispose();
        new view.menus.MenuBiblioteca();
    }

    public static Leitor obterLeitor(int idLeitor) {
        List<Leitor> leitores = manipuladorArquivos.lerLeitores();
        Leitor leitor = leitores.stream()
                .filter(l -> l.getId_leitor() == idLeitor)
                .findFirst()
                .orElse(null);
        return leitor;
    }
}
