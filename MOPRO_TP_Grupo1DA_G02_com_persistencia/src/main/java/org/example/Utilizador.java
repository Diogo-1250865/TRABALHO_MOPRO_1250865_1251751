package org.example;

import java.io.Serializable;
import java.util.Objects;

/**
 * Classe abstrata que representa um utilizador da plataforma.
 */
public abstract class Utilizador implements Serializable {
    /**
     * Atributo que armazena username.
     */
    private String username;
    /**
     * Atributo que armazena email.
     */
    private String email;
    /**
     * Atributo que armazena password.
     */
    private String password;

    private static final String USERNAME_POR_OMISSAO = "Sem username";
    private static final String EMAIL_POR_OMISSAO    = "Sem email";
    private static final String PASSWORD_POR_OMISSAO = "Sem password";

    /**
    * Executa a operação correspondente.
    */
    public Utilizador() {
        this.username = USERNAME_POR_OMISSAO;
        this.email    = EMAIL_POR_OMISSAO;
        this.password = PASSWORD_POR_OMISSAO;
    }

    /**
    * Executa a operação correspondente.
    *
    * @param username valor utilizado pela operação
    * @param email valor utilizado pela operação
    * @param password valor utilizado pela operação
    */
    public Utilizador(String username, String email, String password) {
        this.username = username;
        this.email    = email;
        this.password = password;
    }

    /**
    * Executa a operação correspondente.
    *
    * @param u valor utilizado pela operação
    */
    public Utilizador(Utilizador u) {
        this.username = u.username;
        this.email    = u.email;
        this.password = u.password;
    }



    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getUsername() { return username; }
    /**
    * Atualiza o valor associado.
    *
    * @param username valor utilizado pela operação
    */
    public void setUsername(String username) { this.username = username; }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getEmail() { return email; }
    /**
    * Atualiza o valor associado.
    *
    * @param email valor utilizado pela operação
    */
    public void setEmail(String email) { this.email = email; }

    /**
    * Devolve o valor associado.
    * @return resultado da operação
    */
    public String getPassword() { return password; }
    /**
    * Atualiza o valor associado.
    *
    * @param password valor utilizado pela operação
    */
    public void setPassword(String password) { this.password = password; }

    /**
    * Verifica as credenciais indicadas.
    *
    * @param username valor utilizado pela operação
    * @param password valor utilizado pela operação
    * @return resultado da operação
    */
    public boolean autenticar(String username, String password) {
        return this.username.equals(username) &&
                this.password.equals(password);
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
        Utilizador that = (Utilizador) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(email, that.email);
    }

    @Override
    /**
    * Devolve o código hash do objeto.
    * @return resultado da operação
    */
    public int hashCode() {
        return Objects.hash(username, email);
    }

    @Override
    /**
    * Devolve uma representação textual do objeto.
    * @return resultado da operação
    */
    public String toString() {
        return "Utilizador{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
