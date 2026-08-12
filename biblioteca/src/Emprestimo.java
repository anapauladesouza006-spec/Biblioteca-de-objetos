import java.util.Date;

public class Emprestimo {
    int id_emprestimo;
    Date data_devolucao;
    int id_livro;
    int id_secretaria;
    int id_leitor;

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
    public int getId_livro() {
        return id_livro;
    }
    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }
    public int getId_secretaria() {
        return id_secretaria;
    }
    public void setId_secretaria(int id_secretaria) {
        this.id_secretaria = id_secretaria;
    }
    public int getId_leitor() {
        return id_leitor;
    }
    public void setId_leitor(int id_leitor) {
        this.id_leitor = id_leitor;
    }
}
