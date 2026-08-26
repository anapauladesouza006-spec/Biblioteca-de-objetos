package util;

import modelo.*;
import javax.swing.*;
import java.io.*;
import java.sql.Time;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class manipuladorArquivos {
    private static final String DIRETORIO = "dados";

    public static void salvarObjeto(String nomeClasse, Object objeto, int camposEsperados) {
        String linhaCSV = "";

        if (objeto instanceof Livro vro) {
            linhaCSV = vro.toCSV();
        } else if (objeto instanceof Reserva r) {
            linhaCSV = r.toCSV();
        } else if (objeto instanceof Leitor l) {
            linhaCSV = l.toCSV();
        } else if (objeto instanceof Secretaria s) {
            linhaCSV = s.toCSV();
        } else if (objeto instanceof Emprestimo e) {
            linhaCSV = e.toCSV();
        }

        salvar(nomeClasse, linhaCSV, camposEsperados);
    }

    public static void atualizarObjeto(String nomeClasse, int id, Object novoObjeto, int camposEsperados) {
        List<String[]> lista = ler(nomeClasse, camposEsperados);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i)[0].equals(Integer.toString(id))) {
                String novaLinha = "";

                if (novoObjeto instanceof Livro vro) {
                    novaLinha = vro.toCSV();
                } else if (novoObjeto instanceof Reserva r) {
                    novaLinha = r.toCSV();
                } else if (novoObjeto instanceof Leitor l) {
                    novaLinha = l.toCSV();
                } else if (novoObjeto instanceof Secretaria s) {
                    novaLinha = s.toCSV();
                } else if (novoObjeto instanceof Emprestimo e) {
                    novaLinha = e.toCSV();
                }

                lista.set(i, novaLinha.split(";"));
                break;
            }
        }

        salvarLista(nomeClasse, lista);
    }

    public static void salvar(String nomeClasse, String linhaCSV, int camposEsperados) {
        try {
            File dir = new File(DIRETORIO);
            if (!dir.exists())
                dir.mkdir();
            FileWriter fw = new FileWriter(new File(dir, nomeClasse + ".csv"), true);
            fw.write(linhaCSV + "\n");
            fw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
        }
    }

    public static List<String[]> ler(String nomeClasse, int camposEsperados) {
        List<String[]> linhas = new ArrayList<>();
        File arq = new File(DIRETORIO, nomeClasse + ".csv");
        if (!arq.exists())
            return linhas;
        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                if (camposEsperados > 0) {
                    if (partes.length == camposEsperados) {
                        linhas.add(partes);
                    }
                } else {
                    linhas.add(partes);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler: " + e.getMessage());
        }
        return linhas;
    }

    public static void salvarLista(String nomeClasse, List<String[]> lista) {
        try {
            FileWriter fw = new FileWriter(new File(DIRETORIO, nomeClasse + ".csv"), false);
            for (String[] campos : lista) {
                fw.write(String.join(";", campos) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao sobrescrever arquivo: " + e.getMessage());
        }
    }

    public static int proximoId(String nomeClasse) {
        List<String[]> lista = ler(nomeClasse, -1);
        int maiorId = 0;

        for (String[] campos : lista) {
            try {
                int id = Integer.parseInt(campos[0]);
                if (id > maiorId)
                    maiorId = id;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Erro ao gerar id: " + e.getMessage());
            }
        }

        return maiorId + 1;
    }

    public static List<Leitor> lerLeitors() {
        List<Leitor> lista = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (String[] campos : ler("Leitor", 5)) {
            try {
                int id_leitor = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String telefone = campos[3];
                String cpf = campos[4];
                lista.add(new Leitor(id_leitor, nome, telefone, cpf));
            } catch (Exception e) {
                System.err.println("Erro ao ler Leitor: " + e + Arrays.toString(campos));
            }
        }
        return lista;
    }

    public static List<Reserva> lerReservas() {
        List<Reserva> lista = new ArrayList<>();
        for (String[] campos : ler("Reserva", 4)) {
            try {
                int id = Integer.parseInt(campos[0]);
                String status = campos[1];
                Date data_retirada = campos[2];
                int id_livro = campos[3];
                int id_leitor = campos[4];
                lista.add(new Reserva(id, status, data_retirada, id_livro, id_leitor));
            } catch (Exception e) {
                System.err.println("Erro ao ler médico: " + Arrays.toString(campos));
            }
        }
        return lista;
    }

    public static List<Livro> lerLivros() {
        List<Livro> lista = new ArrayList<>();
        for (String[] campos : ler("Livro", 4)) {
            try {
                int id = Integer.parseInt(campos[0]);
                String titulo = campos[1];
                String autor = campos[2];
                String genero = campos[3];
                String status = campos[3];
                lista.add(new Livro(id, titulo, autor, genero, status));
            } catch (Exception e) {
                System.err.println("Erro ao ler clínica: " + Arrays.toString(campos));
            }
        }
        return lista;
    }

    public static List<Secretaria> lerSecretarias() {
        List<Secretaria> lista = new ArrayList<>();
        for (String[] campos : ler("Secretaria", 3)) {
            try {
                int id = Integer.parseInt(campos[0]);
                String nome = campos[1];
                String cargo = campos[2];
                String telefone = campos[3];
                String email = campos[4];
                lista.add(new Secretaria(id, nome, cargo, telefone, email));
            } catch (Exception e) {
                System.err.println("Erro ao ler secretaria: " + Arrays.toString(campos));
            }
        }
        return lista;
    }

    public static List<Emprestimo> lerEmprestimos() {
        List<Emprestimo> lista = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (String[] campos : ler("Emprestimo", 6)) {
            try {
                int id = Integer.parseInt(campos[0]);
                Date data_devolucao = sdf.parse(campos[1]);
                int idlivro = Integer.parseInt(campos[2]);
                int id_secretaria = Integer.parseInt(campos[3]);
                int id_leitor = Integer.parseInt(campos[4]);
                lista.add(new Emprestimo(id, data_devolucao, idlivro, id_secretaria, id_leitor));
            } catch (Exception e) {
                System.err.println("Erro ao ler Emprestimo: " + Arrays.toString(campos));
            }
        }
        return lista;
    }
}
