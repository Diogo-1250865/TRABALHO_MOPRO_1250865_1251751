package org.example;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa um comentário feito por um utilizador.
 */
public class Comentario implements Serializable {

    /**
     * Atributo que armazena texto.
     */
    private String texto;
    /**
     * Atributo que armazena usernameUtilizador.
     */
    private String usernameUtilizador;
    /**
     * Atributo que armazena data.
     */
    private String data;

    private static final String TEXTO_POR_OMISSAO = "Sem comentario";
    private static final String USERNAME_POR_OMISSAO = "Sem username";
    private static final String DATA_POR_OMISSAO = "Sem data";

    /**
    * Executa a operação correspondente.
    */
    public Comentario() {
        this.texto = TEXTO_POR_OMISSAO;
        this.usernameUtilizador = USERNAME_POR_OMISSAO;
        this.data = DATA_POR_OMISSAO;
    }

    /**
    * Executa a operação correspondente.
    *
    * @param texto valor utilizado pela operação
    * @param usernameUtilizador valor utilizado pela operação
    * @param data valor utilizado pela operação
    */
    public Comentario(String texto, String usernameUtilizador, String data) {
        this.texto = texto;
        this.usernameUtilizador = usernameUtilizador;
        this.data = data;
    }

    /**
    * Executa a operação correspondente.
    *
    * @param c valor utilizado pela operação
    */
    public Comentario(Comentario c) {
        this.texto = c.texto;
        this.usernameUtilizador = c.usernameUtilizador;
        this.data = c.data;
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getTexto() {
        return texto;
    }

    /**
    * Atualiza o valor associado.
    *
    * @param texto valor utilizado pela operação
    */
    public void setTexto(String texto) {
        this.texto = texto;
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

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getData() {
        return data;
    }

    /**
    * Atualiza o valor associado.
    *
    * @param data valor utilizado pela operação
    */
    public void setData(String data) {
        this.data = data;
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
        Comentario that = (Comentario) o;
        return Objects.equals(texto, that.texto) &&
                Objects.equals(usernameUtilizador, that.usernameUtilizador);
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return Objects.hash(texto, usernameUtilizador);
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return "💬 [" + data + "] " + usernameUtilizador + " diz: \"" + texto + "\"";
    }
}
