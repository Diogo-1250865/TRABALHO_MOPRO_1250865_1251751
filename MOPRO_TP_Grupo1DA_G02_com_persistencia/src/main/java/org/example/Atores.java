package org.example;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa um ator registado na plataforma.
 */
public class Atores implements Pesquisavel, Serializable {

    /**
     * Atributo que armazena nome.
     */
    private String nome;
    /**
     * Atributo que armazena dataNascimento.
     */
    private String dataNascimento;
    /**
     * Atributo que armazena nacionalidade.
     */
    private String nacionalidade;

    private static final String NOME_POR_OMISSAO = "Sem nome";
    private static final String DATA_POR_OMISSAO = "Desconhecida";
    private static final String NACIONALIDADE_POR_OMISSAO = "Desconhecida";

    /**
    * Executa a operação correspondente.
    */
    public Atores() {
        this.nome = NOME_POR_OMISSAO;
        this.dataNascimento = DATA_POR_OMISSAO;
        this.nacionalidade = NACIONALIDADE_POR_OMISSAO;
    }

    /**
    * Executa a operação correspondente.
    *
    * @param nome valor utilizado pela operação
    * @param dataNascimento valor utilizado pela operação
    * @param nacionalidade valor utilizado pela operação
    */
    public Atores(String nome, String dataNascimento, String nacionalidade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nacionalidade = nacionalidade;
    }

    /**
    * Executa a operação correspondente.
    *
    * @param a valor utilizado pela operação
    */
    public Atores(Atores a) {
        this.nome = a.nome;
        this.dataNascimento = a.dataNascimento;
        this.nacionalidade = a.nacionalidade;
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getNome() {
        return nome;
    }

    /**
    * Atualiza o valor associado.
    *
    * @param nome valor utilizado pela operação
    */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getDataNascimento() {
        return dataNascimento;
    }

    /**
    * Atualiza o valor associado.
    *
    * @param dataNascimento valor utilizado pela operação
    */
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getNacionalidade() {
        return nacionalidade;
    }

    /**
    * Atualiza o valor associado.
    *
    * @param nacionalidade valor utilizado pela operação
    */
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    // ── Pesquisavel ───────────────────────────────────────────────────────────

    @Override
    /**
    * Verifica se o objeto corresponde ao texto pesquisado.
    *
    * @param texto valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean correspondeAPesquisa(String texto) {
        return nome.toLowerCase().contains(texto.toLowerCase());
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
        Atores that = (Atores) o;
        return Objects.equals(nome, that.nome) &&
                Objects.equals(dataNascimento, that.dataNascimento);
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return Objects.hash(nome, dataNascimento);
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return "🎭 " + nome + " | Nacionalidade: " + nacionalidade + " | Nascimento: " + dataNascimento;
    }
}