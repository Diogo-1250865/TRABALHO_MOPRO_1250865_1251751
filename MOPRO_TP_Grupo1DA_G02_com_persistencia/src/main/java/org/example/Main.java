package org.example;

import java.io.*;

/**
 * Classe principal da aplicação.
 * É responsável por gerir o ciclo de vida do programa: iniciar o sistema,
 * carregar a persistência de dados (ou os dados iniciais), lançar o menu
 * e guardar o estado final da plataforma antes de encerrar.
 */
public class Main {

    /**
     * Ponto de entrada da aplicação.
     */
    public static void main(String[] args) {
        System.out.println("A iniciar o sistema...");

        // Tenta carregar o estado guardado na sessão anterior
        Plataforma plataforma = carregarFicheiro();

        // Se não encontrar o ficheiro, cria dados de raiz
        if (plataforma == null) {
            plataforma = new Plataforma();
            System.out.println("Ficheiro de dados não encontrado. A carregar dados por omissão...");
            carregarDadosIniciais(plataforma);
        } else {
            System.out.println("Dados anteriores carregados com sucesso!");
        }

        Menu menu = new Menu(plataforma);
        menu.iniciar();

        // ATENÇÃO: Isto só corre se saíres do menu de forma normal (opção 0)
        System.out.println("A guardar o estado da plataforma num ficheiro...");
        guardarFicheiro(plataforma);
    }

    /**
     * Guarda o estado atual da plataforma num ficheiro binário usando serialização.
     */
    public static void guardarFicheiro(Plataforma plataforma) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dados.dat"))) {
            oos.writeObject(plataforma);
        } catch (IOException e) {
            System.out.println("Erro ao guardar os dados: " + e.getMessage());
        }
    }

    /**
     * Tenta ler e reconstruir o objeto Plataforma a partir do ficheiro de dados serializado.
     */
    public static Plataforma carregarFicheiro() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dados.dat"))) {
            return (Plataforma) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * Injeta um conjunto de dados iniciais (atores, filmes, séries, e utilizadores) na plataforma.
     */
    private static void carregarDadosIniciais(Plataforma plataforma) {
        try {
            // ── ATORES ──
            Atores a1 = new Atores("Leonardo DiCaprio", "1974-11-11", "Americana");
            Atores a2 = new Atores("Kate Winslet", "1975-10-05", "Britanica");
            Atores a3 = new Atores("Keanu Reeves", "1964-09-02", "Canadiana");
            Atores a4 = new Atores("Bryan Cranston", "1956-03-07", "Americana");
            Atores a5 = new Atores("Cillian Murphy", "1976-05-25", "Irlandesa");
            Atores a6 = new Atores("Matthew McConaughey", "1969-11-04", "Americana");
            Atores a7 = new Atores("Anne Hathaway", "1982-11-12", "Americana");
            Atores a8 = new Atores("Christian Bale", "1974-01-30", "Britanica");
            Atores a9 = new Atores("Heath Ledger", "1979-04-04", "Australiana");
            Atores a10 = new Atores("Amy Adams", "1974-08-20", "Americana");
            Atores a11 = new Atores("Jeremy Renner", "1971-01-07", "Americana");
            Atores a12 = new Atores("Matt Damon", "1970-10-08", "Americana");
            Atores a13 = new Atores("Mark Ruffalo", "1967-11-22", "Americana");
            Atores a14 = new Atores("Margot Robbie", "1990-07-02", "Australiana");

            plataforma.adicionarAtor(a1); plataforma.adicionarAtor(a2);
            plataforma.adicionarAtor(a3); plataforma.adicionarAtor(a4);
            plataforma.adicionarAtor(a5); plataforma.adicionarAtor(a6);
            plataforma.adicionarAtor(a7); plataforma.adicionarAtor(a8);
            plataforma.adicionarAtor(a9); plataforma.adicionarAtor(a10);
            plataforma.adicionarAtor(a11); plataforma.adicionarAtor(a12);
            plataforma.adicionarAtor(a13); plataforma.adicionarAtor(a14);

            // ── FILMES ──
            Filmes f1 = new Filmes("Titanic", Genero.DRAMA, 1997, 195);
            f1.adicionarAtor(a1); f1.adicionarAtor(a2);
            f1.adicionarClassificacao(new Classificacao(9, "joao"));

            Filmes f2 = new Filmes("Matrix", Genero.ACAO, 1999, 136);
            f2.adicionarAtor(a3);
            f2.adicionarClassificacao(new Classificacao(10, "admin"));

            Filmes f3 = new Filmes("Interstellar", Genero.FICAO_CIENTIFICA, 2014, 169);
            f3.adicionarAtor(a6); f3.adicionarAtor(a7); f3.adicionarAtor(a12);
            f3.adicionarClassificacao(new Classificacao(10, "eric09"));

            Filmes f4 = new Filmes("O Cavaleiro das Trevas", Genero.ACAO, 2008, 152);
            f4.adicionarAtor(a8); f4.adicionarAtor(a9); f4.adicionarAtor(a5);
            f4.adicionarClassificacao(new Classificacao(10, "joao"));

            Filmes f5 = new Filmes("Inception", Genero.FICAO_CIENTIFICA, 2010, 148);
            f5.adicionarAtor(a1); f5.adicionarAtor(a5);
            f5.adicionarClassificacao(new Classificacao(9, "maria"));

            Filmes f6 = new Filmes("Arrival", Genero.FICAO_CIENTIFICA, 2016, 116);
            f6.adicionarAtor(a10); f6.adicionarAtor(a11);
            f6.adicionarClassificacao(new Classificacao(10, "eric09"));
            f6.adicionarComentario(new Comentario("Final incrivelmente bem pensado!", "eric09", "2026-05-23"));

            Filmes f7 = new Filmes("Le Mans '66", Genero.ACAO, 2019, 152);
            f7.adicionarAtor(a8); f7.adicionarAtor(a12);
            f7.adicionarClassificacao(new Classificacao(9, "ines"));

            Filmes f8 = new Filmes("Shutter Island", Genero.TERROR, 2010, 138);
            f8.adicionarAtor(a1); f8.adicionarAtor(a13);
            f8.adicionarClassificacao(new Classificacao(9, "eric09"));

            Filmes f9 = new Filmes("O Lobo de Wall Street", Genero.COMEDIA, 2013, 180);
            f9.adicionarAtor(a1); f9.adicionarAtor(a14); f9.adicionarAtor(a6);
            f9.adicionarClassificacao(new Classificacao(8, "joao"));

            plataforma.adicionarFilme(f1); plataforma.adicionarFilme(f2);
            plataforma.adicionarFilme(f3); plataforma.adicionarFilme(f4);
            plataforma.adicionarFilme(f5); plataforma.adicionarFilme(f6);
            plataforma.adicionarFilme(f7); plataforma.adicionarFilme(f8);
            plataforma.adicionarFilme(f9);

            // ── SÉRIES ──
            Serie s1 = new Serie("Breaking Bad", Genero.DRAMA, 2008, 45);
            Temporada t1s1 = new Temporada(1, 2008);
            Episodio ep1s1 = new Episodio(1, "Pilot", 45);
            ep1s1.adicionarAtores(a4);
            Episodio ep2s1 = new Episodio(2, "Cat's in the Bag", 48);
            ep2s1.adicionarAtores(a4);
            t1s1.adicionarEpisodio(ep1s1);
            t1s1.adicionarEpisodio(ep2s1);
            s1.adicionarTemporada(t1s1);
            plataforma.adicionarSerie(s1);

            Serie s2 = new Serie("Peaky Blinders", Genero.DRAMA, 2013, 60);
            Temporada t1s2 = new Temporada(1, 2013);
            Episodio ep1s2 = new Episodio(1, "Episode 1", 57);
            ep1s2.adicionarAtores(a5);
            Episodio ep2s2 = new Episodio(2, "Episode 2", 59);
            ep2s2.adicionarAtores(a5);
            t1s2.adicionarEpisodio(ep1s2);
            t1s2.adicionarEpisodio(ep2s2);
            s2.adicionarTemporada(t1s2);
            plataforma.adicionarSerie(s2);

            Serie s3 = new Serie("True Detective", Genero.DRAMA, 2014, 55);
            Temporada t1s3 = new Temporada(1, 2014);
            Episodio ep1s3 = new Episodio(1, "The Long Bright Dark", 60);
            ep1s3.adicionarAtores(a6);
            t1s3.adicionarEpisodio(ep1s3);
            s3.adicionarTemporada(t1s3);
            plataforma.adicionarSerie(s3);

            // ── UTILIZADORES ──
            UtilizadorRegistrado u1 = new UtilizadorRegistrado("joao", "joao@email.com", "1234");
            UtilizadorRegistrado u2 = new UtilizadorRegistrado("maria", "maria@email.com", "1234");
            UtilizadorRegistrado u3 = new UtilizadorRegistrado("eric09", "eric@email.com", "1234");
            UtilizadorRegistrado u4 = new UtilizadorRegistrado("ines", "ines@email.com", "1234");
            Administrador admin = new Administrador("admin", "admin@email.com", "admin123");

            // ── FIX: Vinculação de Filmes Vistos no Estado Inicial ──
            // Marcar explicitamente todos os filmes como vistos para o eric09
            u3.marcarFilmeComoVisto(f1); u3.marcarFilmeComoVisto(f2);
            u3.marcarFilmeComoVisto(f3); u3.marcarFilmeComoVisto(f4);
            u3.marcarFilmeComoVisto(f5); u3.marcarFilmeComoVisto(f6);
            u3.marcarFilmeComoVisto(f7); u3.marcarFilmeComoVisto(f8);
            u3.marcarFilmeComoVisto(f9);

            // Sincronizar o histórico dos restantes utilizadores com as notas deles
            u1.marcarFilmeComoVisto(f1); u1.marcarFilmeComoVisto(f4); u1.marcarFilmeComoVisto(f9);
            u2.marcarFilmeComoVisto(f5);
            u4.marcarFilmeComoVisto(f7);
            // ────────────────────────────────────────────────────────

            plataforma.adicionarUtilizador(u1);
            plataforma.adicionarUtilizador(u2);
            plataforma.adicionarUtilizador(u3);
            plataforma.adicionarUtilizador(u4);
            plataforma.adicionarUtilizador(admin);

        } catch (RecursoDuplicado | ClassificacaoInvalidaException | AtorNaoAssociadoException e) {
            System.out.println("Erro ao carregar dados iniciais: " + e.getMessage());
        }
    }
}