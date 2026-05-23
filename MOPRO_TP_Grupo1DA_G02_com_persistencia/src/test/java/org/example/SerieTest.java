package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SerieTest {

    @Test
    public void testarAvaliacaoQualitativaSerieFraca() {
        // 1. Prepara os dados
        Serie serie = new Serie("Série de Teste", Genero.COMEDIA, 2025, 40);

        try {
            // Damos uma nota 4 à série
            serie.adicionarClassificacao(new Classificacao(4, "utilizador_teste"));
        } catch (ClassificacaoInvalidaException e) {
            fail("Erro inesperado ao criar a classificação.");
        }

        // 2. Executa o método
        String avaliacao = serie.getAvaliacaoQualitativa();

        // 3. Verifica o resultado
        // Se fosse um filme, nota 4 seria "Médio". Como é Série, o enunciado diz que <5 é "Fraco".
        assertEquals("Fraco", avaliacao, "Uma série com média 4.0 devia ser classificada como 'Fraco'.");
    }
}