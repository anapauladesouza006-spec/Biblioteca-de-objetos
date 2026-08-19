public class Leitor {
    private int id_leitor;
    private String nome;
    private String telefone;
    private String cpf;

    public Leitor (int id, String nome,String telefone,String cpf){
        this.id_leitor = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }
    public void solicitar(Leitor leitor) {

    }

    public void pagarMulta(Leitor leitor) {

    }

    public int getId_leitor() {
        return id_leitor;
    }
    public void setId_leitor(int id_leitor) {
        this.id_leitor = id_leitor;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
     @Override
    public String toString(){
        return id_leitor + nome + telefone + cpf;
     }

}
