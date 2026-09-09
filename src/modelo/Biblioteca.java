package modelo;

import util.manipuladorArquivos;

public class Biblioteca {
        private int id_biblioteca;
        private String nomeBiblioteca;
        private String endereco;
        private String telefone;

        public Biblioteca(int id_biblioteca, String nomeBiblioteca, String endereco, String telefone){
            this.id_biblioteca = id_biblioteca;
            this.nomeBiblioteca = nomeBiblioteca;
            this.endereco = endereco;
            this.telefone = telefone;
        }

        public int getId_biblioteca() {
            return id_biblioteca;
        }

        public void setId_biblioteca(int id_biblioteca) {
            this.id_biblioteca = id_biblioteca;
        }

        public String getNomeBiblioteca() {
            return nomeBiblioteca;
        }

        public void setNomeBiblioteca(String nomeBiblioteca) {
            this.nomeBiblioteca = nomeBiblioteca;
        }

        public String getEndereco() {
            return endereco;
        }

        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public String getTelefone() {
            return telefone;
        }

        public void setTelefone(String telefone) {
            this.telefone = telefone;
        }

        public void atualizarBiblioteca(String novoNome, String novoTelefone, String novoEndereco){
            this.nomeBiblioteca = novoNome;
            this.telefone = novoTelefone;
            this.endereco = novoEndereco;
        }

        public void cadastrarLeitor(Leitor novoLeitor){
            manipuladorArquivos.salvarObjeto("Leitor", novoLeitor, 4);
        }

        public void cadastrarSecretaria(Secretaria novaSecretaria){
            manipuladorArquivos.salvarObjeto("Secretaria", novaSecretaria, 3);
        }

    public void cadastrarLivro(Livro novoLivro){
        manipuladorArquivos.salvarObjeto("Livro", novoLivro, 3);
    }

        @Override
        public String toString(){
            return "ID: " + id_biblioteca + "Nome: " + nomeBiblioteca + "Endereço: " + endereco + "Telefone: " + telefone;
        }

        public String toCSV(){
            return String.join(";", Integer.toString(id_biblioteca), nomeBiblioteca, endereco, telefone);
        }
    }
