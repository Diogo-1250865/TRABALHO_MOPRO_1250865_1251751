package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Representa a lista de favoritos de um utilizador registado.
 */
public class Favoritos implements Serializable {

    /**
     * Atributo que armazena filmes.
     */
    private ArrayList<Filmes> filmes;
    /**
     * Atributo que armazena series.
     */
    private ArrayList<Serie>  series;

    /**
    * Executa a operação correspondente.
    */
    public Favoritos() {
        this.filmes = new ArrayList<>();
        this.series = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param f valor utilizado pela operação
    */
    public Favoritos(Favoritos f) {
        this.filmes = new ArrayList<>(f.filmes);
        this.series = new ArrayList<>(f.series);
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Filmes> getFilmes() { return new ArrayList<>(filmes); }
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Serie>  getSeries() { return new ArrayList<>(series); }

    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param filme valor utilizado pela operação
    */
    public void adicionarFilme(Filmes filme) {
        if (!filmes.contains(filme))
            filmes.add(filme);
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
    * @param serie valor utilizado pela operação
    */
    public void adicionarSerie(Serie serie) {
        if (!series.contains(serie))
            series.add(serie);
    }

    /**
    * Remove um elemento da coleção correspondente.
    *
    * @param serie valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean removerSerie(Serie serie) {
        return series.remove(serie);
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
        Favoritos that = (Favoritos) o;
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
        return "Favoritos{" +
                "filmes=" + filmes +
                ", series=" + series +
                '}';
    }
}
