package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe abstrata que representa um recurso comum da plataforma.
 */
public abstract class Recurso implements Pesquisavel, Serializable {

    /**
     * Atributo que armazena titulo.
     */
    private String titulo;
    /**
     * Atributo que armazena genero.
     */
    private Genero genero; // ← Alterado para Enum
    /**
     * Atributo que armazena ano.
     */
    private int ano;
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

    private static final String TITULO_POR_OMISSAO  = "Sem titulo";
    private static final Genero GENERO_POR_OMISSAO  = Genero.DESCONHECIDO; // ← Alterado para Enum
    private static final int    ANO_POR_OMISSAO     = 0;
    private static final double DURACAO_POR_OMISSAO = 0.0;

    /**
    * Executa a operação correspondente.
    */
    public Recurso() {
        this.titulo         = TITULO_POR_OMISSAO;
        this.genero         = GENERO_POR_OMISSAO;
        this.ano            = ANO_POR_OMISSAO;
        this.duracao        = DURACAO_POR_OMISSAO;
        this.atores         = new ArrayList<>();
        this.classificacoes = new ArrayList<>();
        this.comentarios    = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param titulo valor utilizado pela operação
    * @param genero valor utilizado pela operação
    * @param ano valor utilizado pela operação
    * @param duracao valor utilizado pela operação
    */
    public Recurso(String titulo, Genero genero, int ano, double duracao) {
        this.titulo         = titulo;
        this.genero         = genero;
        this.ano            = ano;
        this.duracao        = duracao;
        this.atores         = new ArrayList<>();
        this.classificacoes = new ArrayList<>();
        this.comentarios    = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param a valor utilizado pela operação
    */
    public Recurso(Recurso a) {
        this.titulo         = a.titulo;
        this.genero         = a.genero;
        this.ano            = a.ano;
        this.duracao        = a.duracao;
        this.atores         = new ArrayList<>(a.atores);
        this.classificacoes = new ArrayList<>(a.classificacoes);
        this.comentarios    = new ArrayList<>(a.comentarios);
    }

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
    public Genero getGenero() { return genero; }
    /**
    * Atualiza o valor associado.
    *
    * @param genero valor utilizado pela operação
    */
    public void setGenero(Genero genero) { this.genero = genero; }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public int getAno() { return ano; }
    /**
    * Atualiza o valor associado.
    *
    * @param ano valor utilizado pela operação
    */
    public void setAno(int ano) { this.ano = ano; }

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

    // ── Correção de Encapsulamento nas Listas ──
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Atores>        getAtores()         { return new ArrayList<>(atores); }
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Classificacao> getClassificacoes() { return new ArrayList<>(classificacoes); }
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Comentario>    getComentarios()    { return new ArrayList<>(comentarios); }

    // ── Pesquisavel ───────────────────────────────────────────────────────────
    @Override
    /**
    * Verifica se o objeto corresponde ao texto pesquisado.
    *
    * @param texto valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean correspondeAPesquisa(String texto) {
        return titulo.toLowerCase().contains(texto.toLowerCase());
    }

    // ── Métodos ───────────────────────────────────────────────────────────────
    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param ator valor utilizado pela operação
    */
    public void adicionarAtor(Atores ator) {
        if (!atores.contains(ator))
            atores.add(ator);
    }

    /**
    * Remove um elemento da coleção correspondente.
    *
    * @param ator valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean removerAtor(Atores ator) {
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

    // ── OBRIGATÓRIO (Polimorfismo para a classificação qualitativa) ──
    public abstract String getAvaliacaoQualitativa();

    @Override
    /**
    * Compara este objeto com outro objeto.
    *
    * @param o valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Recurso recurso = (Recurso) o;
        return ano == recurso.ano &&
                Double.compare(duracao, recurso.duracao) == 0 &&
                Objects.equals(titulo, recurso.titulo) &&
                genero == recurso.genero; // Comparação de Enum é com ==
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return Objects.hash(titulo, genero, ano, duracao);
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return String.format("%s (%d) | Género: %s | Duração: %.0f min | ⭐ Nota: %.1f/10 (%s)",
                titulo, ano, genero.toString(), duracao, calcularClassificacaoMedia(), getAvaliacaoQualitativa());
    }
}