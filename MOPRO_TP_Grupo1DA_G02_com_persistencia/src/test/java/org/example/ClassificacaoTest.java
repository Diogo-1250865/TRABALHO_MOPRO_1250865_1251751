package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClassificacaoTest {

    @Test
    public void testarClassificacaoInvalida() {
        // O assertThrows verifica se o código lá dentro lança a exceção esperada.
        // Se lançar, o teste passa (fica verde). Se não lançar, o teste falha.
        assertThrows(ClassificacaoInvalidaException.class, () -> {
            // Tentamos criar uma classificação com nota 15
            new Classificacao(15, "joao123");
        }, "Deveria lançar uma ClassificacaoInvalidaException ao usar a nota 15.");
    }

    @Test
    public void testarClassificacaoValida() {
        // Aqui testamos o oposto: uma nota válida (7) não pode dar erro.
        try {
            Classificacao c = new Classificacao(7, "maria_silva");
            assertEquals(7, c.getValor(), "O valor guardado devia ser 7.");
            assertEquals("maria_silva", c.getUsernameUtilizador(), "O username devia ser maria_silva.");
        } catch (ClassificacaoInvalidaException e) {
            fail("O teste falhou porque lançou erro com uma nota válida (7).");
        }
    }
}