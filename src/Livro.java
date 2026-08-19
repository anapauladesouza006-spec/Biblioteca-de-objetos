public class Livro {
    private int id_livro;
    private String titulo;
    private String autor;
    private String genero;
    private String status;

    public Livro(int id, String titulo, String autor, String genero, String status){
        this.id_livro = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.status = status;
    }

    public void emprestar(Livro livro) {

    }

    public void devolver(Livro livro) {

    }

    public int getId_livro() {
        return id_livro;
    }

    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return id_livro + titulo + autor + genero + status;
    }
}