package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Emprestimo {
    private int id_emprestimo;
    private Date data_devolucao;
    private Livro livro;
    private Secretaria secretaria;
    private Leitor leitor;
    private boolean ativa;

    public Emprestimo(int id, Date data_devolucao, Livro livro, Secretaria secretaria, Leitor leitor, boolean ativa) {
        this.id_emprestimo = id;
        this.data_devolucao = data_devolucao;
        this.livro = livro;
        this.secretaria = secretaria;
        this.leitor = leitor;
        this.ativa = ativa;
    }

    public void reservar(Livro livro) {

    }

    public void devolver(Livro livro) {

    }

    public int getId_emprestimo() {
        return id_emprestimo;
    }

    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }

    public Date getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Date data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Secretaria getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(Secretaria secretaria) {
        this.secretaria = secretaria;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.join(";", String.valueOf(id_emprestimo), sdf.format(data_devolucao),
                String.valueOf(this.getLivro().getId_livro()), String.valueOf(this.getSecretaria().getId_secretaria()),
                String.valueOf(this.getLeitor().getId_leitor()),
                String.valueOf(ativa));
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return id_emprestimo + " - " + sdf.format(data_devolucao) + " - " + livro + " - " + secretaria + " - " + leitor + " - " + ativa;
    }
}
