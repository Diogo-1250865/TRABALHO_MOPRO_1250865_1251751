package org.example;

// JaClassificouException.java
/**
 * Exceção lançada quando um utilizador tenta classificar novamente o mesmo recurso.
 */
public class JaClassificouException extends Exception {
    /**
    * Executa a operação correspondente.
    *
    * @param username valor utilizado pela operação
    * @param titulo valor utilizado pela operação
    */
    public JaClassificouException(String username, String titulo) {
        super("O utilizador '" + username + "' ja classificou '" + titulo + "'.");
    }
}
