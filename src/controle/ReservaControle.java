package controle;

import modelo.Leitor;
import modelo.Livro;
import modelo.Reserva;
import util.manipuladorArquivos;
import view.menus.MenuLeitor;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReservaControle {

    // CADASTRAR
    public static void cadastrarReserva(
            String dataRetirada,
            int idLivro,
            int idLeitor,
            String status,
            JFrame tela,
            int idSecretaria) {

        if (dataRetirada.isEmpty() ||
                idLivro == 0 ||
                idLeitor == 0 ||
                status.isEmpty()) {

            JOptionPane.showMessageDialog(
                    tela,
                    "Preencha todos os campos.");

            return;
        }

        SimpleDateFormat sdf =
                new SimpleDateFormat("dd/MM/yyyy");

        sdf.setLenient(false);

        Date data;

        try {

            data = sdf.parse(dataRetirada);

        } catch (ParseException e) {

            JOptionPane.showMessageDialog(
                    tela,
                    "Informe uma data válida no formato dd/MM/yyyy.");

            return;
        }

        Livro livro =
                LivroControle.obterLivro(idLivro);

        Leitor leitor =
                LeitorControle.obterLeitor(idLeitor);

        if (livro == null) {

            JOptionPane.showMessageDialog(
                    tela,
                    "Livro não encontrado.");

            return;
        }

        if (leitor == null) {

            JOptionPane.showMessageDialog(
                    tela,
                    "Leitor não encontrado.");

            return;
        }

        int id =
                manipuladorArquivos.proximoId("Reserva");

        Reserva reserva =
                new Reserva(
                        id,
                        status,
                        data,
                        livro,
                        leitor,
                        true);

        if (idSecretaria != 0) {

            SecretariaControle
                    .obterSecretaria(idSecretaria)
                    .cadastrarReserva(reserva);

        } else {

            leitor.cadastrarReserva(reserva);
        }

        JOptionPane.showMessageDialog(
                tela,
                "Reserva realizada com sucesso!");

        tela.dispose();

        if (idSecretaria != 0) {

            new view.menus.MenuSecretaria(
                    idSecretaria);

        } else {

            new view.menus.MenuLeitor(
                    idLeitor);
        }
    }

    // LISTAR
    public static List<Reserva> listarReservas() {

        return manipuladorArquivos.lerReservas();
    }

    // LISTAR RESERVAS PENDENTES
    public static List<Reserva> listarReservasPendentes() {

        return manipuladorArquivos
                .lerReservas()
                .stream()
                .filter(r ->
                        r.getAtiva()
                                && r.getStatus()
                                .equalsIgnoreCase("Ativa"))
                .toList();
    }

    // ALTERAR
    public static void editarReserva(
            int idReserva,
            String novaData,
            MenuLeitor menuLeitor) {

        if (novaData.isEmpty()) {

            JOptionPane.showMessageDialog(
                    menuLeitor,
                    "Informe a nova data.");

            return;
        }

        SimpleDateFormat sdf =
                new SimpleDateFormat("dd/MM/yyyy");

        sdf.setLenient(false);

        Date data;

        try {

            data = sdf.parse(novaData);

        } catch (ParseException e) {

            JOptionPane.showMessageDialog(
                    menuLeitor,
                    "Informe uma data válida no formato dd/MM/yyyy.");

            return;
        }

        Reserva reserva = null;

        List<Reserva> reservas =
                manipuladorArquivos.lerReservas();

        for (Reserva r : reservas) {

            if (r.getid_reserva() == idReserva) {

                reserva = r;
                break;
            }
        }

        if (reserva == null) {

            JOptionPane.showMessageDialog(
                    menuLeitor,
                    "Reserva não encontrada.");

            return;
        }

        reserva.setData_retirada(data);

        manipuladorArquivos.atualizarObjeto(
                "Reserva",
                idReserva,
                reserva,
                6);

        JOptionPane.showMessageDialog(
                menuLeitor,
                "Reserva alterada com sucesso!");
    }

    // EXCLUIR
    public static void excluirReserva(
            int idReserva,
            MenuLeitor menuLeitor) {

        Reserva reserva = null;

        List<Reserva> reservas =
                manipuladorArquivos.lerReservas();

        for (Reserva r : reservas) {

            if (r.getid_reserva() == idReserva) {

                reserva = r;
                break;
            }
        }

        if (reserva == null) {

            JOptionPane.showMessageDialog(
                    menuLeitor,
                    "Reserva não encontrada.");

            return;
        }

        int resposta =
                JOptionPane.showConfirmDialog(
                        menuLeitor,
                        "Deseja realmente excluir esta reserva?",
                        "Confirmar exclusão",
                        JOptionPane.YES_NO_OPTION);

        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

        manipuladorArquivos.excluirObjeto(
                "Reserva",
                idReserva);

        JOptionPane.showMessageDialog(
                menuLeitor,
                "Reserva excluída com sucesso!");
    }

    public static List<Reserva> listarReservasLeitor(int id_leitor) {
        return manipuladorArquivos.lerReservas().stream()
        .filter(r -> r.getLeitor().getId_leitor() == id_leitor)
        .toList();
    }
}