package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Representa a lista pessoal de filmes e episódios de um utilizador.
 */
public class Watchlist implements Serializable {

    /**
     * Atributo que armazena filmes.
     */
    private ArrayList<Filmes>    filmes;
    /**
     * Atributo que armazena episodios.
     */
    private ArrayList<Episodio> episodios;

    /**
    * Executa a operação correspondente.
    */
    public Watchlist() {
        this.filmes    = new ArrayList<>();
        this.episodios = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param w valor utilizado pela operação
    */
    public Watchlist(Watchlist w) {
        this.filmes    = new ArrayList<>(w.filmes);
        this.episodios = new ArrayList<>(w.episodios);
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Filmes>    getFilmes()    { return new ArrayList<>(filmes); }
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Episodio> getEpisodios() { return new ArrayList<>(episodios); }

    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param filme valor utilizado pela operação
    */
    public void adicionarFilme(Filmes filme) {
        if (!filmes.contains(filme)) {
            filmes.add(filme);
        }
    }

    /**
    * Remove um elemento da coleção correspondente.
    *
    * @param filme valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean removerFilme(Filmes filme) {
        return filmes.remove(filme);
    }

    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param episodio valor utilizado pela operação
    */
    public void adicionarEpisodio(Episodio episodio) {
        if (!episodios.contains(episodio)) {
            episodios.add(episodio);
        }
    }

    /**
    * Remove um elemento da coleção correspondente.
    *
    * @param episodio valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean removerEpisodio(Episodio episodio) {
        return episodios.remove(episodio);
    }

    @Override
    /**
    * Compara este objeto com outro objeto.
    *
    * @param o valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Watchlist that = (Watchlist) o;
        return Objects.equals(filmes, that.filmes) &&
                Objects.equals(episodios, that.episodios);
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return Objects.hash(filmes, episodios);
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return "Watchlist{" +
                "filmes=" + filmes +
                ", episodios=" + episodios +
                '}';
    }
}

