import java.util.Date;
import java.text.SimpleDateFormat;

public class App {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        Date data = new Date();

    Emprestimo e = new Emprestimo(1, df.parse("30/02/2026"), 1,1,1);
    Leitor l = new Leitor(1, "Ana Paula", "11 54654654", "03516943272");
    Livro vro = new Livro(1, "O Morro dos Pedros Uivantes", "Pedro Uivando","Feminino", "Casado");
    Reserva r = new Reserva(1, "Passivo", df.parse("01/03/2026"), 1, 1);
    Secretaria s = new Secretaria(1, "Diana", "Que trabalha o dia inteiro", "999949999", "amaisfoda@uol.com.br");

        System.out.println("Emprestimo: " + e);
        System.out.println("Leitor: "+ l);
        System.out.println("Livro: " + vro);
        System.out.println("Reserva: " + r);
        System.out.println("Secretaria: " + s);

    }
}
