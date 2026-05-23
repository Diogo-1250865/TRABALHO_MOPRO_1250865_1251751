package org.example;

import java.util.ArrayList;

/**
 * Representa um utilizador registado com permissões de interação.
 */
public class UtilizadorRegistrado extends Utilizador implements MarcavelComoVisto {

    /**
     * Atributo que armazena watchlist.
     */
    private Watchlist           watchlist;
    /**
     * Atributo que armazena favoritos.
     */
    private Favoritos           favoritos;
    /**
     * Atributo que armazena filmesVistos.
     */
    private ArrayList<Filmes>   filmesVistos;
    /**
     * Atributo que armazena episodiosVistos.
     */
    private ArrayList<Episodio> episodiosVistos;

    /**
    * Executa a operação correspondente.
    */
    public UtilizadorRegistrado() {
        super();
        this.watchlist       = new Watchlist();
        this.favoritos       = new Favoritos();
        this.filmesVistos    = new ArrayList<>();
        this.episodiosVistos = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param username valor utilizado pela operação
    * @param email valor utilizado pela operação
    * @param password valor utilizado pela operação
    */
    public UtilizadorRegistrado(String username, String email, String password) {
        super(username, email, password);
        this.watchlist       = new Watchlist();
        this.favoritos       = new Favoritos();
        this.filmesVistos    = new ArrayList<>();
        this.episodiosVistos = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param u valor utilizado pela operação
    */
    public UtilizadorRegistrado(UtilizadorRegistrado u) {
        super(u);
        this.watchlist       = new Watchlist(u.watchlist);
        this.favoritos       = new Favoritos(u.favoritos);
        this.filmesVistos    = new ArrayList<>(u.filmesVistos);
        this.episodiosVistos = new ArrayList<>(u.episodiosVistos);
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public Watchlist           getWatchlist()       { return watchlist; }
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public Favoritos           getFavoritos()       { return favoritos; }
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Filmes>   getFilmesVistos()    { return new ArrayList<>(filmesVistos); }
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Episodio> getEpisodiosVistos() { return new ArrayList<>(episodiosVistos); }

    // ── Watchlist ────────────────────────────────────────────────────────────

    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param filme valor utilizado pela operação
    */
    public void adicionarFilmeLista(Filmes filme) {
        watchlist.adicionarFilme(filme);
    }

    /**
    * Remove um elemento da coleção correspondente.
    *
    * @param filme valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean removerFilmeLista(Filmes filme) {
        return watchlist.removerFilme(filme);
    }

    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param episodio valor utilizado pela operação
    */
    public void adicionarEpisodioLista(Episodio episodio) {
        watchlist.adicionarEpisodio(episodio);
    }

    /**
    * Remove um elemento da coleção correspondente.
    *
    * @param episodio valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean removerEpisodioLista(Episodio episodio) {
        return watchlist.removerEpisodio(episodio);
    }

    // ── Favoritos ────────────────────────────────────────────────────────────

    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param filme valor utilizado pela operação
    */
    public void adicionarFilmeFavorito(Filmes filme) {
        favoritos.adicionarFilme(filme);
    }

    /**
    * Remove um elemento da coleção correspondente.
    *
    * @param filme valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean removerFilmeFavorito(Filmes filme) {
        return favoritos.removerFilme(filme);
    }

    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param serie valor utilizado pela operação
    */
    public void adicionarSerieFavorita(Serie serie) {
        favoritos.adicionarSerie(serie);
    }

    /**
    * Remove um elemento da coleção correspondente.
    *
    * @param serie valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean removerSerieFavorita(Serie serie) {
        return favoritos.removerSerie(serie);
    }

    // ── Marcar como visto (Interface MarcavelComoVisto) ──────────────────────

    @Override
    /**
    * Marca o elemento de acordo com a operação indicada.
    *
    * @param filme valor utilizado pela operação
    */
    public void marcarFilmeComoVisto(Filmes filme) {
        if (!filmesVistos.contains(filme))
            filmesVistos.add(filme);
    }

    @Override
    /**
    * Marca o elemento de acordo com a operação indicada.
    *
    * @param episodio valor utilizado pela operação
    */
    public void marcarEpisodioComoVisto(Episodio episodio) {
        if (!episodiosVistos.contains(episodio))
            episodiosVistos.add(episodio);
    }

    /**
    * Remove um elemento da coleção correspondente.
    *
    * @param filme valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean removerFilmeDosVistos(Filmes filme) {
        return filmesVistos.remove(filme);
    }

    @Override
    /**
    * Verifica se a condição indicada é verdadeira.
    *
    * @param filme valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean jaViuFilme(Filmes filme) {
        return filmesVistos.contains(filme);
    }

    @Override
    /**
    * Verifica se a condição indicada é verdadeira.
    *
    * @param episodio valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean jaViuEpisodio(Episodio episodio) {
        return episodiosVistos.contains(episodio);
    }

    // ── Classificar ──────────────────────────────────────────────────────────


    // ── Classificar ──────────────────────────────────────────────────────────


    public void classificarFilme(Filmes filme, int valor)
            throws NaoVistoException, JaClassificouException, ClassificacaoInvalidaException {

        if (!jaViuFilme(filme)) {
            throw new NaoVistoException(getUsername(), filme.getTitulo());
        }

        for (Classificacao c : filme.getClassificacoes()) {
            if (c.getUsernameUtilizador().equals(getUsername())) {
                throw new JaClassificouException(getUsername(), filme.getTitulo());
            }
        }


        filme.adicionarClassificacao(new Classificacao(valor, getUsername()));
    }

    public void classificarEpisodio(Episodio episodio, int valor)
            throws NaoVistoException, JaClassificouException, ClassificacaoInvalidaException {

        if (!jaViuEpisodio(episodio)) {
            throw new NaoVistoException(getUsername(), episodio.getTitulo());
        }

        for (Classificacao c : episodio.getClassificacoes()) {
            if (c.getUsernameUtilizador().equals(getUsername())) {
                throw new JaClassificouException(getUsername(), episodio.getTitulo());
            }
        }

        episodio.adicionarClassificacao(new Classificacao(valor, getUsername()));
    }
    // ── Comentar ─────────────────────────────────────────────────────────────

    /**
    * Regista um comentário no recurso indicado.
    *
    * @param filme valor utilizado pela operação
    * @param texto valor utilizado pela operação
    * @param data valor utilizado pela operação
    */
    public void comentarFilme(Filmes filme, String texto, String data) {
        filme.adicionarComentario(new Comentario(texto, getUsername(), data));
    }

    /**
    * Regista um comentário no recurso indicado.
    *
    * @param episodio valor utilizado pela operação
    * @param texto valor utilizado pela operação
    * @param data valor utilizado pela operação
    */
    public void comentarEpisodio(Episodio episodio, String texto, String data) {
        episodio.adicionarComentario(new Comentario(texto, getUsername(), data));
    }

    /**
    * Executa a operação correspondente.
    * @return resultado da operação
    */
    public int totalFilmesVistos() {
        return filmesVistos.size();
    }

    @Override
    /**
    * Compara este objeto com outro objeto.
    *
    * @param o valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return "UtilizadorRegistrado{" +
                super.toString() +
                ", filmesVistos=" + filmesVistos.size() +
                ", watchlist=" + watchlist +
                ", favoritos=" + favoritos +
                '}';
    }
}