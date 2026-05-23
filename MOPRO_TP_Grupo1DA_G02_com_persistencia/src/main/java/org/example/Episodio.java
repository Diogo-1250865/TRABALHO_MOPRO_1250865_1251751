package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Representa um episódio de uma temporada.
 */
public class Episodio implements Serializable {

    /**
     * Atributo que armazena numero.
     */
    private int numero;
    /**
     * Atributo que armazena titulo.
     */
    private String titulo;
    /**
     * Atributo que armazena duracao.
     */
    private double duracao;
    /**
     * Atributo que armazena atores.
     */
    private ArrayList<Atores>        atores;
    /**
     * Atributo que armazena classificacoes.
     */
    private ArrayList<Classificacao> classificacoes;
    /**
     * Atributo que armazena comentarios.
     */
    private ArrayList<Comentario>    comentarios;

    private static final int     NUMERO_POR_OMISSAO  = 1;
    private static final String  TITULO_POR_OMISSAO  = "Sem titulo";
    private static final double  DURACAO_POR_OMISSAO = 0.0;

    /**
    * Executa a operação correspondente.
    */
    public Episodio() {
        this.numero         = NUMERO_POR_OMISSAO;
        this.titulo         = TITULO_POR_OMISSAO;
        this.duracao        = DURACAO_POR_OMISSAO;
        this.atores         = new ArrayList<>();
        this.classificacoes = new ArrayList<>();
        this.comentarios    = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param numero valor utilizado pela operação
    * @param titulo valor utilizado pela operação
    * @param duracao valor utilizado pela operação
    */
    public Episodio(int numero, String titulo, double duracao) {
        this.numero         = numero;
        this.titulo         = titulo;
        this.duracao        = duracao;
        this.atores         = new ArrayList<>();
        this.classificacoes = new ArrayList<>();
        this.comentarios    = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param e valor utilizado pela operação
    */
    public Episodio(Episodio e) {
        this.numero         = e.numero;
        this.titulo         = e.titulo;
        this.duracao        = e.duracao;
        this.atores         = new ArrayList<>(e.atores);
        this.classificacoes = new ArrayList<>(e.classificacoes);
        this.comentarios    = new ArrayList<>(e.comentarios);
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public int getNumero() { return numero; }
    /**
    * Atualiza o valor associado.
    *
    * @param numero valor utilizado pela operação
    */
    public void setNumero(int numero) { this.numero = numero; }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getTitulo() { return titulo; }
    /**
    * Atualiza o valor associado.
    *
    * @param titulo valor utilizado pela operação
    */
    public void setTitulo(String titulo) { this.titulo = titulo; }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public double getDuracao() { return duracao; }
    /**
    * Atualiza o valor associado.
    *
    * @param duracao valor utilizado pela operação
    */
    public void setDuracao(double duracao) { this.duracao = duracao; }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Atores>        getAtores()         { return atores; }
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Classificacao> getClassificacoes() { return classificacoes; }
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Comentario>    getComentarios()    { return comentarios; }

    // ── Métodos ───────────────────────────────────────────────────────────────

    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param ator valor utilizado pela operação
    */
    public void adicionarAtores(Atores ator) {
        if (!atores.contains(ator))
            atores.add(ator);
    }

    /**
    * Remove um elemento da coleção correspondente.
    *
    * @param ator valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean removerAtores(Atores ator) {
        return atores.remove(ator);
    }

    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param c valor utilizado pela operação
    */
    public void adicionarClassificacao(Classificacao c) {
        classificacoes.add(c);
    }

    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param c valor utilizado pela operação
    */
    public void adicionarComentario(Comentario c) {
        comentarios.add(c);
    }

    /**
    * Calcula o valor correspondente.
    * @return resultado da operação
    */
    public double calcularClassificacaoMedia() {
        if (classificacoes.isEmpty()) return 0;
        double soma = 0;
        for (Classificacao c : classificacoes)
            soma += c.getValor();
        return soma / classificacoes.size();
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
        Episodio that = (Episodio) o;
        return numero == that.numero &&
                Objects.equals(titulo, that.titulo);
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return Objects.hash(numero, titulo);
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return "Episodio{" +
                "numero=" + numero +
                ", titulo='" + titulo + '\'' +
                ", duracao=" + duracao +
                ", classificacao=" + calcularClassificacaoMedia() +
                '}';
    }
}