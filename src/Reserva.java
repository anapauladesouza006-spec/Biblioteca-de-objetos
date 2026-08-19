import java.util.Date;

public class Reserva {
    private int id_reseva;
    private String status;
    private Date data_retirada;
    private int id_livro;
    private int id_leitor;

    public Reserva(int id, String status, Date data_retirada, int id_livro, int id_leitor){
        this.id_reseva = id;
        this.status = status;
        this.data_retirada = data_retirada;
        this.id_livro = id_livro;
        this.id_leitor  = id_leitor;
    }

    public void reservar(Livro livro) {

    }

    public void devolver(Livro livro) {

    }

    public int getId_reseva() {
        return id_reseva;
    }

    public void setId_reseva(int id_reseva) {
        this.id_reseva = id_reseva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getData_retirada() {
        return data_retirada;
    }

    public void setData_retirada(Date data_retirada) {
        this.data_retirada = data_retirada;
    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public int getId_leitor() {
        return id_leitor;
    }

    public void setId_leitor(int id_leitor) {
        this.id_leitor = id_leitor;
    }

    @Override
    public String toString(){
        return id_reseva + status + data_retirada.toString() + id_livro + id_leitor;

    }
}
