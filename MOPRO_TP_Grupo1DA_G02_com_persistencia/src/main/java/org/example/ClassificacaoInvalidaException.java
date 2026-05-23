package org.example;

// ClassificacaoInvalidaException.java
/**
 * Exceção lançada quando a classificação introduzida não é válida.
 */
public class ClassificacaoInvalidaException extends Exception {
    /**
    * Executa a operação correspondente.
    *
    * @param valor valor utilizado pela operação
    */
    public ClassificacaoInvalidaException(int valor) {
        super("A classificacao " + valor + " e invalida. Deve estar entre 1 e 10.");
    }
}
