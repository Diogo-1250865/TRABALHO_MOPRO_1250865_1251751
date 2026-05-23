package org.example;

import java.util.Objects;

// Vê aqui a mudança: agora herda de UtilizadorRegistrado
/**
 * Representa um administrador da plataforma, com permissões para gerir recursos, atores e utilizadores.
 */
public class Administrador extends UtilizadorRegistrado {

    /**
    * Executa a operação correspondente.
    */
    public Administrador() {
        super();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param username valor utilizado pela operação
    * @param email valor utilizado pela operação
    * @param password valor utilizado pela operação
    */
    public Administrador(String username, String email, String password) {
        super(username, email, password);
    }

    /**
    * Executa a operação correspondente.
    *
    * @param a valor utilizado pela operação
    */
    public Administrador(Administrador a) {
        super(a);
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
        return "Administrador{" +
                super.toString() +
                '}';
    }
}