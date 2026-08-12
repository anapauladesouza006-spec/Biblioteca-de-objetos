public class Livro {
    int id_livro;
    String titulo;
    String autor;
    String genero;
    String status;

    public void emprestar(Livro livro) {

    }

    public void devolver(Livro livro) {

    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_livro() {
        return id_livro;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public String getStatus() {
        return status;
    }

}
