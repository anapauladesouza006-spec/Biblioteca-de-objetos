package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
    private int id_reserva;
    private String status;
    private Date data_retirada;
    private Livro livro;
    private Leitor leitor;

    public Reserva(int id, String status, Date data_retirada, Livro livro, Leitor leitor) {
        this.id_reserva = id;
        this.status = status;
        this.data_retirada = data_retirada;
        this.livro = livro;
        this.leitor = leitor;
    }

    public void reservar(Livro livro) {

    }

    public void devolver(Livro livro) {

    }

    public int getid_reserva() {
        return id_reserva;
    }

    public void setid_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
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

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public String toCSV() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.join(";", String.valueOf(id_reserva), status, sdf.format(data_retirada),
                String.valueOf(this.getLivro().getId_livro()),
                String.valueOf(this.getLeitor().getId_leitor()));
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return id_reserva + " " + status + " " + sdf.format(data_retirada) + " " + livro + " " + leitor;
    }
}
