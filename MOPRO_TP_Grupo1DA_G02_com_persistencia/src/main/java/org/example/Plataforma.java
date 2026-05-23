package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.io.*;

/**
 * A classe Plataforma atua como o motor central e a base de dados em memória do sistema.
 * É responsável por armazenar e gerir todas as listas de filmes, séries, atores e utilizadores.
 * Implementa a interface Serializable para permitir a gravação e leitura do estado do sistema em ficheiros.
 */
public class Plataforma implements Serializable {

    /**
     * Atributo que armazena filmes.
     */
    private ArrayList<Filmes> filmes;
    /**
     * Atributo que armazena series.
     */
    private ArrayList<Serie> series;
    /**
     * Atributo que armazena atores.
     */
    private ArrayList<Atores> atores;
    /**
     * Atributo que armazena utilizadores.
     */
    private ArrayList<Utilizador> utilizadores;

    /**
     * Construtor por omissão da Plataforma.
     * Inicializa as listas de filmes, séries, atores e utilizadores como listas vazias.
     */
    public Plataforma() {
        this.filmes = new ArrayList<>();
        this.series = new ArrayList<>();
        this.atores = new ArrayList<>();
        this.utilizadores = new ArrayList<>();
    }

    /**
     * Devolve uma cópia da lista de filmes registados na plataforma.
     * @return Um ArrayList contendo os filmes.
     */
    public ArrayList<Filmes> getFilmes() {
        return new ArrayList<>(filmes);
    }

    /**
     * Devolve uma cópia da lista de séries registadas na plataforma.
     * @return Um ArrayList contendo as séries.
     */
    public ArrayList<Serie> getSeries() {
        return new ArrayList<>(series);
    }

    /**
     * Devolve uma cópia da lista de atores registados na plataforma.
     * @return Um ArrayList contendo os atores.
     */
    public ArrayList<Atores> getAtores() {
        return new ArrayList<>(atores);
    }

    /**
     * Devolve uma cópia da lista de utilizadores registados na plataforma.
     * @return Um ArrayList contendo os utilizadores.
     */
    public ArrayList<Utilizador> getUtilizadores() {
        return new ArrayList<>(utilizadores);
    }

    // ── Filmes ───────────────────────────────────────────────────────────────

    /**
     * Adiciona um novo filme à plataforma.
     * @param filme O filme a ser adicionado.
     * @throws RecursoDuplicado Se já existir um filme com o mesmo título e ano na plataforma.
     * @throws AtorNaoAssociadoException Se o filme não tiver nenhum ator associado.
     */
    public void adicionarFilme(Filmes filme) throws RecursoDuplicado, AtorNaoAssociadoException {
        if (filme.getAtores().isEmpty()) {
            throw new AtorNaoAssociadoException(filme.getTitulo());
        }
        if (filmes.contains(filme)) {
            throw new RecursoDuplicado(filme.getTitulo(), filme.getAno());
        }
        filmes.add(filme);
    }

    /**
     * Remove um filme da plataforma.
     * @param filme O filme a ser removido.
     * @return true se o filme foi removido com sucesso, false caso o filme não exista na lista.
     */
    public boolean removerFilme(Filmes filme) {
        return filmes.remove(filme);
    }

    /**
     * Procura e devolve um filme específico com base no seu título e ano de lançamento.
     * @param titulo O título do filme.
     * @param ano O ano de lançamento do filme.
     * @return O objeto Filmes correspondente, ou null se não for encontrado.
     */
    public Filmes getFilme(String titulo, int ano) {
        for (Filmes f : filmes) {
            if (f.getTitulo().equalsIgnoreCase(titulo) && f.getAno() == ano)
                return f;
        }
        return null;
    }

    /**
     * Pesquisa filmes cujo título contenha uma determinada palavra ou frase.
     * @param texto O texto a pesquisar no título dos filmes.
     * @return Uma lista de filmes que correspondem à pesquisa.
     */
    public ArrayList<Filmes> pesquisarFilmes(String texto) {
        ArrayList<Filmes> resultado = new ArrayList<>();
        for (Filmes f : filmes) {
            if (f.getTitulo().toLowerCase().contains(texto.toLowerCase()))
                resultado.add(f);
        }
        return resultado;
    }

    /**
     * Conta o número total de participações de um ator (soma das participações em filmes e em episódios de séries).
     * @param ator O ator cujas participações vão ser contadas.
     * @return O número inteiro correspondente ao total de participações.
     */
    public int contarParticipacoes(Atores ator) {
        int count = 0;

        for (Filmes f : filmes) {
            if (f.getAtores().contains(ator)) count++;
        }

        for (Serie s : series) {
            for (Temporada t : s.getTemporadas()) {
                for (Episodio e : t.getEpisodios()) {
                    if (e.getAtores().contains(ator)) count++;
                }
            }
        }
        return count;
    }

    /**
     * Ordena a lista de atores de forma decrescente com base no número total de participações.
     * @return Uma lista de atores ordenada do mais ativo para o menos ativo.
     */
    public ArrayList<Atores> atoresOrdenadosPorParticipacoes() {
        ArrayList<Atores> lista = new ArrayList<>(atores);
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (contarParticipacoes(lista.get(i)) < contarParticipacoes(lista.get(j))) {
                    Atores temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
        }
        return lista;
    }

    /**
     * Ordena os utilizadores registados de forma decrescente pelo número de filmes que já viram.
     * @return Uma lista de utilizadores ordenada dos que viram mais filmes para os que viram menos.
     */
    public ArrayList<UtilizadorRegistrado> utilizadoresComMaisFilmesVistos() {
        ArrayList<UtilizadorRegistrado> lista = new ArrayList<>();
        // Filtrar apenas os utilizadores registados
        for (Utilizador u : utilizadores) {
            if (u instanceof UtilizadorRegistrado) {
                lista.add((UtilizadorRegistrado) u);
            }
        }
        // Ordenar de forma decrescente
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).totalFilmesVistos() < lista.get(j).totalFilmesVistos()) {
                    UtilizadorRegistrado temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
        }
        return lista;
    }

    /**
     * Ordena os filmes da plataforma alfabeticamente pelo título.
     * @return Uma lista de filmes ordenada de A a Z.
     */
    public ArrayList<Filmes> filmesOrdenadosPorTitulo() {
        ArrayList<Filmes> lista = new ArrayList<>(filmes);
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).getTitulo().compareToIgnoreCase(
                        lista.get(j).getTitulo()) > 0) {
                    Filmes temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
        }
        return lista;
    }

    /**
     * Ordena os filmes da plataforma de forma decrescente com base na sua classificação média.
     * @return Uma lista de filmes ordenada da melhor nota para a pior nota.
     */
    public ArrayList<Filmes> filmesOrdenadosPorClassificacao() {
        ArrayList<Filmes> lista = new ArrayList<>(filmes);
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).calcularClassificacaoMedia() <
                        lista.get(j).calcularClassificacaoMedia()) {
                    Filmes temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
        }
        return lista;
    }

    // ── Series ───────────────────────────────────────────────────────────────

    /**
     * Adiciona uma nova série à plataforma.
     * @param serie A série a ser adicionada.
     * @throws RecursoDuplicado Se já existir uma série com o mesmo título e ano.
     */
    public void adicionarSerie(Serie serie) throws RecursoDuplicado {
        if (series.contains(serie)) {
            throw new RecursoDuplicado(serie.getTitulo(), serie.getAno());
        }
        series.add(serie);
    }

    /**
     * Remove uma série da plataforma.
     * @param serie A série a ser removida.
     * @return true se a série foi removida com sucesso, false caso contrário.
     */
    public boolean removerSerie(Serie serie) {
        return series.remove(serie);
    }

    /**
     * Procura e devolve uma série específica através do seu título e ano.
     * @param titulo O título da série.
     * @param ano O ano de lançamento.
     * @return A série correspondente ou null se não for encontrada.
     */
    public Serie getSerie(String titulo, int ano) {
        for (Serie s : series) {
            if (s.getTitulo().equalsIgnoreCase(titulo) && s.getAno() == ano)
                return s;
        }
        return null;
    }

    /**
     * Pesquisa séries cujo título contenha um determinado texto.
     * @param texto O texto a procurar.
     * @return Lista de séries que correspondem ao critério de pesquisa.
     */
    public ArrayList<Serie> pesquisarSeries(String texto) {
        ArrayList<Serie> resultado = new ArrayList<>();
        for (Serie s : series) {
            if (s.getTitulo().toLowerCase().contains(texto.toLowerCase()))
                resultado.add(s);
        }
        return resultado;
    }

    // ── Atores ───────────────────────────────────────────────────────────────

    /**
     * Regista um novo ator no sistema, evitando duplicações exatas.
     * @param ator O ator a ser adicionado.
     */
    public void adicionarAtor(Atores ator) {
        if (!atores.contains(ator))
            atores.add(ator);
    }

    /**
     * Remove um ator do sistema.
     * @param ator O ator a ser removido.
     * @return true se foi removido, false caso não exista.
     */
    public boolean removerAtor(Atores ator) {
        return atores.remove(ator);
    }

    /**
     * Procura um ator pelo seu nome exato (ignorando maiúsculas e minúsculas).
     * @param nome O nome do ator.
     * @return O ator encontrado ou null.
     */
    public Atores getAtor(String nome) {
        for (Atores a : atores) {
            if (a.getNome().equalsIgnoreCase(nome))
                return a;
        }
        return null;
    }

    /**
     * Pesquisa atores cujo nome contenha o texto fornecido.
     * @param texto O texto a procurar no nome dos atores.
     * @return Lista de atores correspondentes.
     */
    public ArrayList<Atores> pesquisarAtores(String texto) {
        ArrayList<Atores> resultado = new ArrayList<>();
        for (Atores a : atores) {
            if (a.getNome().toLowerCase().contains(texto.toLowerCase()))
                resultado.add(a);
        }
        return resultado;
    }

    /**
     * Ordena a lista de atores alfabeticamente pelo nome.
     * @return Lista de atores ordenada de A a Z.
     */
    public ArrayList<Atores> atoresOrdenadosPorNome() {
        ArrayList<Atores> lista = new ArrayList<>(atores);
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(i).getNome().compareToIgnoreCase(
                        lista.get(j).getNome()) > 0) {
                    Atores temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }
        }
        return lista;
    }

    // ── Utilizadores ─────────────────────────────────────────────────────────

    /**
     * Adiciona um novo utilizador (seja UtilizadorRegistrado ou Administrador) ao sistema.
     * @param utilizador O utilizador a registar.
     */
    public void adicionarUtilizador(Utilizador utilizador) {
        if (!utilizadores.contains(utilizador))
            utilizadores.add(utilizador);
    }

    /**
     * Remove um utilizador do sistema.
     * @param utilizador O utilizador a remover.
     * @return true se foi removido com sucesso, false caso contrário.
     */
    public boolean removerUtilizador(Utilizador utilizador) {
        return utilizadores.remove(utilizador);
    }

    /**
     * Procura um utilizador específico através do seu username de registo.
     * @param username O nome de utilizador a pesquisar.
     * @return O utilizador encontrado, ou null se não existir.
     */
    public Utilizador getUtilizador(String username) {
        for (Utilizador u : utilizadores) {
            if (u.getUsername().equalsIgnoreCase(username))
                return u;
        }
        return null;
    }

    // ── Ficheiros e Serialização ─────────────────────────────────────────────

    /**
     * Serializa e guarda o estado atual de toda a plataforma num ficheiro físico.
     * @param nomeFicheiro O caminho/nome do ficheiro onde os dados serão guardados (ex: "dados.dat").
     */
    public void guardarEmFicheiro(String nomeFicheiro) {
        try {
            FileOutputStream ficheiro = new FileOutputStream(nomeFicheiro);
            ObjectOutputStream oos = new ObjectOutputStream(ficheiro);

            oos.writeObject(this); // Guarda a plataforma toda de uma vez

            oos.close(); // Fechar o stream "à mão" (típico do 1º ano)
            ficheiro.close();

        } catch (IOException e) {
            System.out.println("Erro ao guardar os dados no ficheiro: " + e.getMessage());
        }
    }

    /**
     * Carrega uma instância da Plataforma previamente guardada em ficheiro através de desserialização.
     * @param nomeFicheiro O nome do ficheiro a ler.
     * @return A Plataforma recuperada com todos os dados.
     * @throws IOException Se houver um erro de leitura do ficheiro.
     * @throws ClassNotFoundException Se as classes correspondentes não forem encontradas no projeto.
     */
    public static Plataforma carregarDeFicheiro(String nomeFicheiro) throws IOException, ClassNotFoundException {
        FileInputStream ficheiro = new FileInputStream(nomeFicheiro);
        ObjectInputStream ois = new ObjectInputStream(ficheiro);

        Plataforma p = (Plataforma) ois.readObject();

        ois.close(); // Fechar à mão
        ficheiro.close();

        return p;
    }

    // ── Métodos Overrides ────────────────────────────────────────────────────

    @Override
    /**
    * Compara este objeto com outro objeto.
    *
    * @param o valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Plataforma that = (Plataforma) o;
        return Objects.equals(filmes, that.filmes) &&
                Objects.equals(series, that.series);
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return Objects.hash(filmes, series);
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return "Plataforma{" +
                "filmes=" + filmes.size() +
                ", series=" + series.size() +
                ", atores=" + atores.size() +
                ", utilizadores=" + utilizadores.size() +
                '}';
    }
}