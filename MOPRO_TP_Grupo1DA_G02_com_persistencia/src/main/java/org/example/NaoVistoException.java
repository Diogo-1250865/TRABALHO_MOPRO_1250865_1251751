package org.example;

// NaoVistoException.java
/**
 * Exceção lançada quando um utilizador tenta interagir com um recurso ainda não visto.
 */
public class NaoVistoException extends Exception {
    /**
    * Executa a operação correspondente.
    *
    * @param username valor utilizado pela operação
    * @param titulo valor utilizado pela operação
    */
    public NaoVistoException(String username, String titulo) {
        super("O utilizador '" + username + "' ainda nao viu '" + titulo + "'.");
    }
}
