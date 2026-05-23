package org.example;

// RecursoDuplicadoException.java
/**
 * Exceção lançada quando se tenta adicionar um recurso duplicado.
 */
public class RecursoDuplicado extends Exception {
    /**
    * Executa a operação correspondente.
    *
    * @param titulo valor utilizado pela operação
    * @param ano valor utilizado pela operação
    */
    public RecursoDuplicado(String titulo, int ano) {
        super("Ja existe um recurso com o titulo '" + titulo + "' e ano " + ano + ".");
    }
}
