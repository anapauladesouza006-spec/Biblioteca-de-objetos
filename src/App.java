import java.util.ArrayList;
import java.util.Date;

import modelo.Emprestimo;
import modelo.Leitor;
import modelo.Livro;
import modelo.Reserva;
import modelo.Secretaria;
import util.manipuladorArquivos;

import java.text.SimpleDateFormat;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        List<Emprestimo> emprestimos = new ArrayList<>();
        List<Leitor> leitores = new ArrayList<>();
        List<Livro> livros = new ArrayList<>();
        List<Reserva> reservas = new ArrayList<>();
        List<Secretaria> secretarias = new ArrayList<>();


        emprestimos = manipuladorArquivos.lerEmprestimos();
        leitores = manipuladorArquivos.lerLeitores();
        livros = manipuladorArquivos.lerLivros();
        reservas = manipuladorArquivos.lerReservas();
        secretarias = manipuladorArquivos.lerSecretarias();

        int novoIdE = manipuladorArquivos.proximoId("Emprestimo");
        int novoIdL = manipuladorArquivos.proximoId("Leitor");
        int novoIdVro = manipuladorArquivos.proximoId("Livro");
        int novoIdR = manipuladorArquivos.proximoId("Reserva");
        int novoIdS = manipuladorArquivos.proximoId("Secretaria");


        Emprestimo e = new Emprestimo(novoIdE, df.parse("24/08/2006"), 1, 1, 1);
        Leitor l = new Leitor(novoIdL, "Ana Paula", "11 54654654", "03516943272");
        Livro vro = new Livro(novoIdVro, "O Morro dos Ventos Uivantes", "Pedro Henrique", "Romance", "Emprestado");
        Reserva r = new Reserva(novoIdR, "Reservado", df.parse("02/01/2006"), 1, 1);
        Secretaria s = new Secretaria(novoIdS, "Diana", "Auxiliadora Administrativa", "999949999", "diana@uol.com.br");

        manipuladorArquivos.salvarObjeto("Emprestimo", e, 5);
        manipuladorArquivos.salvarObjeto("Leitor", l, 4);
        manipuladorArquivos.salvarObjeto("Livro", vro, 6);
        manipuladorArquivos.salvarObjeto("Reserva", r, 5);
        manipuladorArquivos.salvarObjeto("Secretaria", s, 5);

        emprestimos.add(e);
        leitores.add(l);
        livros.add(vro);
        reservas.add(r);
        secretarias.add(s);

        System.out.println("Emprestimos:");

        for(Emprestimo percorre : emprestimos){
            System.out.println(percorre.toString());
        }

        System.out.println("\n----------------------------------");
        System.out.println("Leitores:");

        for(Leitor percorre : leitores){
            System.out.println(percorre.toString());
        }

        System.out.println("\n----------------------------------");
        System.out.println("Livros:");

        for(Livro percorre : livros){
            System.out.println(percorre.toString());
        }

        System.out.println("\n----------------------------------");
        System.out.println("Reservas:");

        for(Reserva percorre : reservas){
            System.out.println(percorre.toString());
        }

        System.out.println("\n----------------------------------");
        System.out.println("Secretarias:");

        for(Secretaria percorre : secretarias){
            System.out.println(percorre.toString());
        }

        System.out.println("\n----------------------------------");

    }
}
