package org.example;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Representa uma série registada na plataforma.
 */
public class Serie extends Recurso implements Serializable {

    /**
     * Atributo que armazena temporadas.
     */
    private ArrayList<Temporada> temporadas;

    /**
    * Executa a operação correspondente.
    */
    public Serie() {
        super();
        this.temporadas = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param titulo valor utilizado pela operação
    * @param genero valor utilizado pela operação
    * @param ano valor utilizado pela operação
    * @param duracao valor utilizado pela operação
    */
    public Serie(String titulo, Genero genero, int ano, double duracao) {
        super(titulo, genero, ano, duracao);
        this.temporadas = new ArrayList<>();
    }

    /**
    * Executa a operação correspondente.
    *
    * @param s valor utilizado pela operação
    */
    public Serie(Serie s) {
        super(s);
        this.temporadas = new ArrayList<>(s.temporadas);
    }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public ArrayList<Temporada> getTemporadas() {
        return new ArrayList<>(temporadas);
    }

    /**
    * Adiciona um elemento à coleção correspondente.
    *
    * @param temporada valor utilizado pela operação
    */
    public void adicionarTemporada(Temporada temporada) {
        if (!temporadas.contains(temporada)) {
            temporadas.add(temporada);
        }
    }

    /**
    * Remove um elemento da coleção correspondente.
    *
    * @param temporada valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean removerTemporada(Temporada temporada) {
        return temporadas.remove(temporada);
    }

    /**
    * Devolve o valor associado.
    *
    * @param numero valor utilizado pela operação
    * @return resultado da operação
    */
    public Temporada getTemporada(int numero) {
        for (Temporada t : temporadas) {
            if (t.getNumero() == numero) {
                return t;
            }
        }
        return null;
    }

    @Override
    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getAvaliacaoQualitativa() {
        double media = calcularClassificacaoMedia();
        if (media == 0) return "Sem classificação";
        if (media < 5) return "Fraco";
        if (media <= 7.8) return "Médio";
        return "Bom";
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
        if (!super.equals(o)) return false;
        Serie that = (Serie) o;
        return Objects.equals(temporadas, that.temporadas);
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return Objects.hash(super.hashCode(), temporadas);
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return "[📺 Série] " + super.toString() + " | Temporadas: " + temporadas.size();
    }
}