package org.example;

/**
 * Interface que define operações para marcar filmes e episódios como vistos.
 */
public interface MarcavelComoVisto {
    void marcarFilmeComoVisto(Filmes filme);
    void marcarEpisodioComoVisto(Episodio episodio);
    boolean jaViuFilme(Filmes filme);
    boolean jaViuEpisodio(Episodio episodio);
}