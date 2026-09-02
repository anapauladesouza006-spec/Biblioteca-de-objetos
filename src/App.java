import java.util.Date;

import modelo.Emprestimo;
import modelo.Leitor;
import modelo.Livro;
import modelo.Reserva;
import modelo.Secretaria;
import util.manipuladorArquivos;

import java.text.SimpleDateFormat;

public class App {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Emprestimo e = new Emprestimo(1, df.parse("24/08/2006"), 1, 1, 1);
        Leitor l = new Leitor(1, "Ana Paula", "11 54654654", "03516943272");
        Livro vro = new Livro(1, "O Morro dos Ventos Uivantes", "Pedro Henrique", "Romance", "Emprestado");
        Reserva r = new Reserva(1, "Reservado", df.parse("02/01/2006"), 1, 1);
        Secretaria s = new Secretaria(1, "Diana", "Auxiliadora Administrativa", "999949999", "diana@uol.com.br");

        System.out.println("Emprestimo: " + e);
        System.out.println("Leitor: " + l);
        System.out.println("Livro: " + vro);
        System.out.println("Reserva: " + r);
        System.out.println("Secretaria: " + s);
        manipuladorArquivos.salvarObjeto("Emprestimo", e, 5);
        manipuladorArquivos.salvarObjeto("Leitor", l, 4);
        manipuladorArquivos.salvarObjeto("Livro", vro, 6);
        manipuladorArquivos.salvarObjeto("Reserva", r, 5);
        manipuladorArquivos.salvarObjeto("Secretaria", s, 5);

    }
}
