package org.example;

// AtorNaoAssociadoException.java
/**
 * Exceção lançada quando um filme ou episódio não possui atores associados.
 */
public class AtorNaoAssociadoException extends Exception {
    /**
    * Executa a operação correspondente.
    *
    * @param titulo valor utilizado pela operação
    */
    public AtorNaoAssociadoException(String titulo) {
        super("O recurso '" + titulo + "' deve ter pelo menos um ator associado.");
    }
}
