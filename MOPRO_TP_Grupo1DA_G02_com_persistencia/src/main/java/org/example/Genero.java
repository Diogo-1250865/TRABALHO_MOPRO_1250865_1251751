package org.example;

/**
 * Enumeração com géneros permitidos para classificar recursos.
 */
public enum Genero {
    ACAO, COMEDIA, DRAMA, FICAO_CIENTIFICA, TERROR, ROMANCE, DOCUMENTARIO, DESCONHECIDO;

    // Este método serve para quando imprimires no ecrã, em vez de aparecer "FICAO_CIENTIFICA"
    // aparecer formatado de forma bonita: "Ficao cientifica".
    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase().replace("_", " ");
    }
}