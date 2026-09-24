package util;

import controle.LeitorControle;
import controle.LivroControle;
import controle.SecretariaControle;
import modelo.Biblioteca;
import modelo.Emprestimo;
import modelo.Leitor;
import modelo.Livro;
import modelo.Reserva;
import modelo.Secretaria;

import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class manipuladorArquivos {
    private static final String DIRETORIO = "src/dados";

    public static void salvarObjeto(String nomeClasse, Object objeto, int camposEsperados) {
        String linhaCSV = "";

        if (objeto instanceof Livro) {
            linhaCSV = ((Livro) objeto).toCSV();
        } else if (objeto instanceof Reserva) {
            linhaCSV = ((Reserva) objeto).toCSV();
        } else if (objeto instanceof Leitor) {
            linhaCSV = ((Leitor) objeto).toCSV();
        } else if (objeto instanceof Secretaria) {
            linhaCSV = ((Secretaria) objeto).toCSV();
        } else if (objeto instanceof Emprestimo) {
            linhaCSV = ((Emprestimo) objeto).toCSV();
        } else if (objeto instanceof Biblioteca) {
            linhaCSV = ((Biblioteca) objeto).toCSV();
        }

        salvar(nomeClasse, linhaCSV, camposEsperados);
    }

    public static void atualizarObjeto(String nomeClasse, int id, Object novoObjeto, int camposEsperados) {
        List<String[]> lista = ler(nomeClasse, camposEsperados);

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i)[0].equals(String.valueOf(id))) {
                String novaLinha = "";

                if (novoObjeto instanceof Livro) {
                    novaLinha = ((Livro) novoObjeto).toCSV();
                } else if (novoObjeto instanceof Reserva) {
                    novaLinha = ((Reserva) novoObjeto).toCSV();
                } else if (novoObjeto instanceof Leitor) {
                    novaLinha = ((Leitor) novoObjeto).toCSV();
                } else if (novoObjeto instanceof Secretaria) {
                    novaLinha = ((Secretaria) novoObjeto).toCSV();
                } else if (novoObjeto instanceof Emprestimo) {
                    novaLinha = ((Emprestimo) novoObjeto).toCSV();
                } else if (novoObjeto instanceof Biblioteca) {
                    novaLinha = ((Biblioteca) novoObjeto).toCSV();
                }

                lista.set(i, novaLinha.split(";", -1));
                break;
            }
        }

        salvarLista(nomeClasse, lista);
    }

    // EXCLUIR OBJETO

    public static void excluirObjeto(String nomeClasse, int id) {

        List<String[]> lista = ler(nomeClasse, -1);

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i)[0].equals(String.valueOf(id))) {

                lista.remove(i);
                break;
            }
        }

        salvarLista(nomeClasse, lista);
    }

    public static void salvar(String nomeClasse, String linhaCSV, int camposEsperados) {
        try {
            File dir = new File(DIRETORIO);

            if (!dir.exists() && !dir.mkdirs()) {
                throw new IOException("Não foi possível criar o diretório de dados.");
            }

            File arquivo = new File(dir, nomeClasse + ".csv");

            try (FileWriter fw = new FileWriter(arquivo, true)) {
                fw.write(linhaCSV + System.lineSeparator());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + e.getMessage());
        }
    }

    public static List<String[]> ler(String nomeClasse, int camposEsperados) {
        List<String[]> linhas = new ArrayList<>();

        File arq = new File(DIRETORIO, nomeClasse + ".csv");

        if (!arq.exists()) {
            return linhas;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arq))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) {
                    continue;
                }

                String[] partes = linha.split(";", -1);

                if (camposEsperados <= 0 || partes.length == camposEsperados) {
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
            File dir = new File(DIRETORIO);

            if (!dir.exists() && !dir.mkdirs()) {
                throw new IOException("Não foi possível criar o diretório de dados.");
            }

            File arquivo = new File(dir, nomeClasse + ".csv");

            try (FileWriter fw = new FileWriter(arquivo, false)) {
                for (String[] campos : lista) {
                    fw.write(String.join(";", campos) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao sobrescrever arquivo: " + e.getMessage());
        }
    }

    public static int proximoId(String nomeClasse) {
        List<String[]> lista = ler(nomeClasse, -1);

        int maiorId = 0;

        for (String[] campos : lista) {
            if (campos.length == 0) {
                continue;
            }

            try {
                int id = Integer.parseInt(campos[0]);

                if (id > maiorId) {
                    maiorId = id;
                }
            } catch (NumberFormatException ignored) {
            }
        }

        return maiorId + 1;
    }

    public static List<Leitor> lerLeitores() {
        List<Leitor> lista = new ArrayList<>();

        for (String[] campos : ler("Leitor", 4)) {
            try {
                lista.add(new Leitor(
                        Integer.parseInt(campos[0]),
                        campos[1],
                        campos[2],
                        campos[3]));
            } catch (Exception e) {
                System.err.println("Erro ao ler leitor: " + e.getMessage());
            }
        }

        return lista;
    }

    public static List<Reserva> lerReservas() {
        List<Reserva> lista = new ArrayList<>();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        for (String[] campos : ler("Reserva", 5)) {
            try {
                int id = Integer.parseInt(campos[0]);
                String status = campos[1];
                Date dataRetirada = df.parse(campos[2]);
                int idLivro = Integer.parseInt(campos[3]);
                int idLeitor = Integer.parseInt(campos[4]);

                Livro livro = LivroControle.obterLivro(idLivro);
                Leitor leitor = LeitorControle.obterLeitor(idLeitor);

                if (livro != null && leitor != null) {
                    lista.add(new Reserva(id, status, dataRetirada, livro, leitor));
                }
            } catch (Exception e) {
                System.err.println("Erro ao ler reserva: " + e.getMessage());
            }
        }

        return lista;
    }

    public static List<Livro> lerLivros() {
        List<Livro> lista = new ArrayList<>();

        for (String[] campos : ler("Livro", 5)) {
            try {
                lista.add(new Livro(
                        Integer.parseInt(campos[0]),
                        campos[1],
                        campos[2],
                        campos[3],
                        campos[4]));
            } catch (Exception e) {
                System.err.println("Erro ao ler livro: " + e.getMessage());
            }
        }

        return lista;
    }

    public static List<Secretaria> lerSecretarias() {
        List<Secretaria> lista = new ArrayList<>();

        for (String[] campos : ler("Secretaria", 5)) {
            try {
                lista.add(new Secretaria(
                        Integer.parseInt(campos[0]),
                        campos[1],
                        campos[2],
                        campos[3],
                        campos[4]));
            } catch (Exception e) {
                System.err.println("Erro ao ler secretaria: " + e.getMessage());
            }
        }

        return lista;
    }

    public static List<Emprestimo> lerEmprestimos() {
        List<Emprestimo> lista = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        for (String[] campos : ler("Emprestimo", 5)) {
            try {
                int id = Integer.parseInt(campos[0]);
                Date dataDevolucao = sdf.parse(campos[1]);
                int idLivro = Integer.parseInt(campos[2]);
                int idSecretaria = Integer.parseInt(campos[3]);
                int idLeitor = Integer.parseInt(campos[4]);

                Livro livro = LivroControle.obterLivro(idLivro);
                Secretaria secretaria = SecretariaControle.obterSecretaria(idSecretaria);
                Leitor leitor = LeitorControle.obterLeitor(idLeitor);

                if (livro != null && secretaria != null && leitor != null) {
                    lista.add(new Emprestimo(
                            id,
                            dataDevolucao,
                            livro,
                            secretaria,
                            leitor));
                }
            } catch (Exception e) {
                System.err.println("Erro ao ler empréstimo: " + e.getMessage());
            }
        }

        return lista;
    }

    public static List<Biblioteca> lerBibliotecas() {
        List<Biblioteca> lista = new ArrayList<>();

        for (String[] campos : ler("Biblioteca", 4)) {
            try {
                lista.add(new Biblioteca(
                        Integer.parseInt(campos[0]),
                        campos[1],
                        campos[2],
                        campos[3]));
            } catch (Exception e) {
                System.err.println("Erro ao ler biblioteca: " + e.getMessage());
            }
        }

        return lista;
    }
}
