package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Representa uma temporada pertencente a uma série.
 */
public class Temporada implements Serializable {

    /**
     * Atributo que armazena numero.
     */
    private int numero;
    /**
     * Atributo que armazena ano.
     */
    private int ano;
    /**
     * Atributo que armazena episodios.
     */
    private ArrayList<Episodio> episodios;

    private static final int NUMERO_POR_OMISSAO = 1;
    private static final int ANO_POR_OMISSAO = 0;

    /**
    * Executa a operação correspondente.
    */
    public Temporada() {
        this.numero = NUMERO_POR_OMISSAO;
        this.ano = ANO_POR_OMISSAO;
        this.episodios = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param numero valor utilizado pela operação
    * @param ano valor utilizado pela operação
    */
    public Temporada(int numero, int ano) {
        this.numero = numero;
        this.ano = ano;
        this.episodios = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param t valor utilizado pela operação
    */
    public Temporada(Temporada t) {
        this.numero = t.numero;
        this.ano = t.ano;
        this.episodios = new ArrayList<>(t.episodios);
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public int getNumero() {
        return numero;
    }

    /**
    * Atualiza o valor associado.
    *
    * @param numero valor utilizado pela operação
    */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public int getAno() {
        return ano;
    }

    /**
    * Atualiza o valor associado.
    *
    * @param ano valor utilizado pela operação
    */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Episodio> getEpisodios() {
        return episodios;
    }


    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param episodio episódio a adicionar à temporada
    * @throws AtorNaoAssociadoException Se o episódio não tiver nenhum ator associado.
    */
    public void adicionarEpisodio(Episodio episodio) throws AtorNaoAssociadoException {
        if (episodio.getAtores().isEmpty()) {
            throw new AtorNaoAssociadoException(episodio.getTitulo());
        }
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
        Temporada that = (Temporada) o;
        return numero == that.numero &&
                ano == that.ano;
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return Objects.hash(numero, ano);
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return "Temporada{" +
                "numero=" + numero +
                ", ano=" + ano +
                ", episodios=" + episodios +
                '}';
    }
}