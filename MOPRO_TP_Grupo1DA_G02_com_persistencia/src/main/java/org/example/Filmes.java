package org.example;

import java.io.Serializable;

/**
 * Representa um filme registado na plataforma.
 */
public class Filmes extends Recurso implements Serializable {

    /**
    * Executa a operação correspondente.
    */
    public Filmes() {
        super();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param titulo valor utilizado pela operação
    * @param genero valor utilizado pela operação
    * @param ano valor utilizado pela operação
    * @param duracao valor utilizado pela operação
    */
    public Filmes(String titulo, Genero genero, int ano, double duracao) {
        super(titulo, genero, ano, duracao);
    }

    /**
    * Executa a operação correspondente.
    *
    * @param f valor utilizado pela operação
    */
    public Filmes(Filmes f) {
        super(f);
    }


    @Override
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getAvaliacaoQualitativa() {
        double media = calcularClassificacaoMedia();
        if (media == 0) return "Sem classificação";
        if (media < 4) return "Fraco";
        if (media <= 8) return "Médio";
        return "Bom";
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
        return super.equals(o);
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return "[🎬 Filme] " + super.toString();
    }
}