package org.example;

// Importações do JUnit (a biblioteca de testes)
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilmesTest {

    @Test
    public void testarCalculoMedia() {
        // 1. Preparar os dados (Criar um filme e dar-lhe notas)
        Filmes filme = new Filmes("O Padrinho", Genero.DRAMA, 1972, 175);

        try {
            filme.adicionarClassificacao(new Classificacao(10, "joao"));
            filme.adicionarClassificacao(new Classificacao(8, "maria"));
        } catch (ClassificacaoInvalidaException e) {
            fail("O teste falhou porque as notas 10 e 8 são válidas e não deviam dar erro.");
        }

        // 2. Executar o método que queremos testar
        double media = filme.calcularClassificacaoMedia();

        // 3. Verificar o resultado: (10 + 8) / 2 = 9.0
        // O assertEquals compara o valor esperado (9.0) com o valor que o método devolveu
        assertEquals(9.0, media, "A média calculada deveria ser 9.0");
    }

    @Test
    public void testarAvaliacaoQualitativaBom() {
        // 1. Preparar
        Filmes filme = new Filmes("Duna", Genero.FICAO_CIENTIFICA, 2021, 155);

        try {
            filme.adicionarClassificacao(new Classificacao(9, "rui"));
        } catch (ClassificacaoInvalidaException e) {
            fail("Erro inesperado a criar classificação.");
        }

        // 2. Executar
        String avaliacao = filme.getAvaliacaoQualitativa();

        // 3. Verificar (Como a nota é 9, o filme tem de ser "Bom")
        assertEquals("Bom", avaliacao, "O filme com nota 9 devia ter avaliação qualitativa 'Bom'");
    }
}