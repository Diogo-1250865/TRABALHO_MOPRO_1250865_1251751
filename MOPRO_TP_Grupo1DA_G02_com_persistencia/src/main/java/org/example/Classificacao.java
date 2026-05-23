package org.example;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa uma classificação atribuída por um utilizador a um filme ou episódio.
 */
public class Classificacao implements Serializable {

    /**
     * Atributo que armazena valor.
     */
    private int valor;
    /**
     * Atributo que armazena usernameUtilizador.
     */
    private String usernameUtilizador;

    private static final int VALOR_POR_OMISSAO = 1;
    private static final String USERNAME_POR_OMISSAO = "Sem username";

    /**
    * Executa a operação correspondente.
    */
    public Classificacao() {
        this.valor = VALOR_POR_OMISSAO;
        this.usernameUtilizador = USERNAME_POR_OMISSAO;
    }

    // ── CORREÇÃO: Adicionado o "throws" e chamada ao setValor para validar ──
    /**
    * Executa a operação correspondente.
    *
    * @param valor valor utilizado pela operação
    * @param usernameUtilizador valor utilizado pela operação
    * @throws ClassificacaoInvalidaException se a operação não puder ser concluída
    */
    public Classificacao(int valor, String usernameUtilizador) throws ClassificacaoInvalidaException {
        setValor(valor);
        this.usernameUtilizador = usernameUtilizador;
    }

    /**
    * Executa a operação correspondente.
    *
    * @param c valor utilizado pela operação
    */
    public Classificacao(Classificacao c) {
        this.valor = c.valor;
        this.usernameUtilizador = c.usernameUtilizador;
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public int getValor() {
        return valor;
    }

    // ── CORREÇÃO: Lógica que dispara a tua exceção personalizada ──
    /**
    * Atualiza o valor associado.
    *
    * @param valor valor utilizado pela operação
    * @throws ClassificacaoInvalidaException se a operação não puder ser concluída
    */
    public void setValor(int valor) throws ClassificacaoInvalidaException {
        if (valor < 1 || valor > 10) {
            throw new ClassificacaoInvalidaException(valor);
        }
        this.valor = valor;
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getUsernameUtilizador() {
        return usernameUtilizador;
    }

    /**
    * Atualiza o valor associado.
    *
    * @param usernameUtilizador valor utilizado pela operação
    */
    public void setUsernameUtilizador(String usernameUtilizador) {
        this.usernameUtilizador = usernameUtilizador;
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
        Classificacao that = (Classificacao) o;
        return valor == that.valor &&
                Objects.equals(usernameUtilizador, that.usernameUtilizador);
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return Objects.hash(valor, usernameUtilizador);
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return "Classificacao{" +
                "valor=" + valor +
                ", usernameUtilizador='" + usernameUtilizador + '\'' +
                '}';
    }
}