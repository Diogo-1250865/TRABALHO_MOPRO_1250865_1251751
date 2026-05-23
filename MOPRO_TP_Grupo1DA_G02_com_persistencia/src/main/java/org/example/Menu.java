package org.example;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Classe responsável por gerir a interface de linha de comandos (CLI) da plataforma.
 * Permite a navegação entre os diferentes menus e a interação com os dados da plataforma.
 */
public class Menu {

    /**
     * Atributo que armazena plataforma.
     */
    private Plataforma plataforma;
    /**
     * Atributo que armazena sc.
     */
    private Scanner sc;

    /**
     * Construtor da classe Menu.
     * * @param plataforma A instância da plataforma que contém os dados a ser manipulados.
     */
    public Menu(Plataforma plataforma) {
        this.plataforma = plataforma;
        this.sc = new Scanner(System.in);
    }

    /**
     * Inicia o ciclo principal da aplicação, apresentando o menu principal ao utilizador.
     */
    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n╔══════════════════════════╗");
            System.out.println("║   PLATAFORMA DE FILMES   ║");
            System.out.println("╠══════════════════════════╣");
            System.out.println("║ 1. Explorar Filmes       ║");
            System.out.println("║ 2. Explorar Series       ║");
            System.out.println("║ 3. Explorar Atores       ║");
            System.out.println("║ 4. Pesquisar Catálogo    ║");
            System.out.println("║ 5. Criar Conta Nova      ║");
            System.out.println("║ 6. Entrar no Meu Perfil👤║");
            System.out.println("║ 7. Painel de Admin 🔒    ║");
            System.out.println("║ 8. Top/Rankings (Listas) ║");
            System.out.println("║ 0. Sair                  ║");
            System.out.println("╚══════════════════════════╝");
            System.out.print("Opcao: ");
            opcao = lerInt(0, 8);

            switch (opcao) {
                case 1: listarFilmes();       break;
                case 2: listarSeries();       break;
                case 3: listarAtores();       break;
                case 4: menuPesquisa();       break;
                case 5: registarUtilizador(); break;
                case 6: menuInteracoes();     break;
                case 7: menuAdministracao();  break;
                case 8: menuListagens();      break;
                case 0: System.out.println("Ate logo!"); break;
            }
        } while (opcao != 0);
    }

    // ── Filmes ────────────────────────────────────────────────────────────────

    /**
     * Autentica um utilizador com permissões de administrador.
     * * @return true se as credenciais estiverem corretas e o utilizador for administrador, false caso contrário.
     */
    private boolean autenticarAdministrador() {
        System.out.println("\n── ACESSO RESTRITO ──");
        System.out.print("Username de Admin: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        Utilizador u = plataforma.getUtilizador(username);

        if (u == null) {
            System.out.println("Erro: Esse utilizador não existe.");
            return false;
        }

        if (u.getPassword().equals(password) == false) {
            System.out.println("Erro: A password está errada.");
            return false;
        }

        if (u instanceof Administrador) {
            System.out.println("Bem-vindo, " + u.getUsername() + "!");
            return true;
        } else {
            System.out.println("Erro: Não tens permissões de administrador.");
            return false;
        }
    }

    /**
     * Apresenta o menu de administração para gestão de dados da plataforma.
     * Requer autenticação prévia de um administrador.
     */
    private void menuAdministracao() {

        boolean temAcesso = autenticarAdministrador();
        if (temAcesso == false) {
            return;
        }

        int opcao;
        do {
            System.out.println("\n── PAINEL DE ADMIN ──");
            System.out.println("1. Gerir Filmes");
            System.out.println("2. Gerir Series");
            System.out.println("3. Gerir Atores");
            System.out.println("4. Gerir Utilizadores");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt(0, 4);

            switch (opcao) {
                case 1: menuFilmes();       break;
                case 2: menuSeries();       break;
                case 3: menuAtores();       break;
                case 4: menuUtilizadores(); break;
            }
        } while (opcao != 0);
    }

    /**
     * Apresenta o sub-menu de gestão de filmes.
     */
    private void menuFilmes() {
        int opcao;
        do {
            System.out.println("\n── FILMES ──");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Remover");
            System.out.println("4. Associar Ator");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt(0, 4);
            switch (opcao) {
                case 1: adicionarFilme();    break;
                case 2: listarFilmes();      break;
                case 3: removerFilme();      break;
                case 4: associarAtorFilme(); break;
            }
        } while (opcao != 0);
    }

    /**
     * Pede os dados ao utilizador para registar um novo filme no sistema.
     * Obriga à associação imediata de um ator principal, cumprindo as regras de negócio.
     */
    /**
     * Pede os dados ao utilizador para registar um novo filme no sistema.
     * Permite associar um ator existente ou criar um novo no momento.
     */
    /**
     * Pede os dados ao utilizador para registar um novo filme no sistema.
     * Permite pesquisar atores existentes, criar um novo ou escolher da lista geral.
     */
    private void adicionarFilme() {
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.print("Genero: ");
        String genero = sc.nextLine();
        System.out.print("Ano: ");
        int ano = lerInt(1900, 2100);
        System.out.print("Duracao (min): ");
        double duracao = lerInt(1, 600);

        try {
            Filmes novoFilme = new Filmes(titulo, parseGenero(genero), ano, duracao);

            System.out.println("\n[Aviso] Um filme tem de ter pelo menos 1 ator associado.");
            System.out.println("1. 🔍 Pesquisar ator por nome...");

            int totalAtores = plataforma.getAtores().size();
            for (int i = 0; i < totalAtores; i++) {
                Atores a = plataforma.getAtores().get(i);
                System.out.println((i + 2) + ". " + a.getNome());
            }

            int opcaoCriarNovo = totalAtores + 2;
            System.out.println(opcaoCriarNovo + ". ➕ Criar novo ator");
            System.out.println("0. Cancelar");

            System.out.print("Escolhe uma opção: ");
            int escolhaAtor = lerInt(0, opcaoCriarNovo);

            if (escolhaAtor == 0) {
                System.out.println("Criação do filme cancelada.");
                return;
            }

            Atores atorEscolhido = null;

            if (escolhaAtor == 1) {
                System.out.print("\nEscreve parte do nome do ator: ");
                String pesquisa = sc.nextLine().toLowerCase();
                ArrayList<Atores> filtrados = plataforma.pesquisarAtores(pesquisa);

                if (filtrados.isEmpty()) {
                    System.out.println("Nenhum ator encontrado com essa pesquisa.");
                    return;
                }

                System.out.println("\n── Resultados da Pesquisa ──");
                for (int i = 0; i < filtrados.size(); i++) {
                    System.out.println((i + 1) + ". " + filtrados.get(i).getNome());
                }
                System.out.println("0. Cancelar");
                System.out.print("Opção: ");
                int esc = lerInt(0, filtrados.size());
                if (esc == 0) return;
                atorEscolhido = filtrados.get(esc - 1);

            } else if (escolhaAtor == opcaoCriarNovo) {
                System.out.println("\n── CRIAR NOVO ATOR ──");
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Data de nascimento (AAAA-MM-DD): ");
                String data = sc.nextLine();
                System.out.print("Nacionalidade: ");
                String nac = sc.nextLine();

                atorEscolhido = new Atores(nome, data, nac);
                plataforma.adicionarAtor(atorEscolhido);
                System.out.println("Novo ator criado globalmente!");
            } else {
                atorEscolhido = plataforma.getAtores().get(escolhaAtor - 2);
            }

            novoFilme.adicionarAtor(atorEscolhido);
            plataforma.adicionarFilme(novoFilme);
            System.out.println("Filme criado com sucesso!");

        } catch (RecursoDuplicado | AtorNaoAssociadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Lista os filmes da plataforma, permitindo pesquisar por texto no número 1
     * ou selecionar um filme diretamente da lista para consultar os seus comentários.
     */
    private void listarFilmes() {
        if (plataforma.getFilmes().isEmpty()) {
            System.out.println("Sem filmes na plataforma.");
            return;
        }

        System.out.println("\n── Lista de Filmes ──");
        // 1. Injetamos a opção padrão de pesquisa no número 1
        System.out.println("1. 🔍 Pesquisar filme por título...");

        // As opções de filmes passam a estar deslocadas (+2)
        int totalFilmes = plataforma.getFilmes().size();
        for (int i = 0; i < totalFilmes; i++) {
            System.out.println((i + 2) + ". " + plataforma.getFilmes().get(i).toString());
        }

        System.out.println("0. Voltar ao menu anterior");
        System.out.print("\nEscolhe uma opção (ou o número do filme para ver comentários): ");

        int escolha = lerInt(0, totalFilmes + 1);
        if (escolha == 0) return;

        Filmes filmeEscolhido = null;

        if (escolha == 1) {
            // Fluxo de filtragem por texto
            System.out.print("\nEscreve parte do título do filme: ");
            String pesquisa = sc.nextLine().toLowerCase();
            ArrayList<Filmes> filtrados = plataforma.pesquisarFilmes(pesquisa);

            if (filtrados.isEmpty()) {
                System.out.println("Nenhum filme encontrado.");
                return;
            }

            System.out.println("\n── Resultados da Pesquisa ──");
            for (int i = 0; i < filtrados.size(); i++) {
                System.out.println((i + 1) + ". " + filtrados.get(i).toString());
            }
            System.out.println("0. Cancelar");
            System.out.print("Opção: ");
            int esc = lerInt(0, filtrados.size());
            if (esc == 0) return;
            filmeEscolhido = filtrados.get(esc - 1);
        } else {
            // Seleção direta da lista geral (ajustando o índice com -2)
            filmeEscolhido = plataforma.getFilmes().get(escolha - 2);
        }

        // Avança para a visualização detalhada de comentários
        verComentariosDe(filmeEscolhido);
    }

    /**
     * Apresenta os comentários associados a um determinado filme,
     * cruzando os dados para mostrar a nota que o autor do comentário atribuiu.
     * * @param filme O filme cujos comentários vão ser mostrados.
     */
    private void verComentariosDe(Filmes filme) {
        System.out.println("\n── Comentários: " + filme.getTitulo() + " ──");

        if (filme.getComentarios().isEmpty()) {
            System.out.println("  (Ainda não existem comentários para este filme. Sê o primeiro!)");
        } else {
            for (Comentario c : filme.getComentarios()) {
                Integer notaDoUtilizador = null;

                // Procuramos se o dono deste comentário deixou alguma classificação no filme
                for (Classificacao cl : filme.getClassificacoes()) {
                    if (cl.getUsernameUtilizador().equals(c.getUsernameUtilizador())) {
                        notaDoUtilizador = cl.getValor();
                        break; // Match encontrado, podemos parar o loop interno
                    }
                }

                // Constrói o sufixo com a nota obtida de forma elegante
                String textoNota = (notaDoUtilizador != null)
                        ? " | ⭐ Nota dada: " + notaDoUtilizador + "/10"
                        : " | ⭐ Não classificou";

                // Exibe o comentário original concatenado com a respetiva avaliação
                System.out.println("  " + c.toString() + textoNota);
            }
        }

        // Pausa pequena para o utilizador conseguir ler antes de o menu aparecer outra vez
        System.out.println("\n(Pressiona ENTER para voltar)");
        sc.nextLine(); // Consome a linha vazia
    }

    /**
     * Remove um filme da plataforma através do seu título e ano.
     */
    private void removerFilme() {
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.print("Ano: ");
        int ano = lerInt(1900, 2100);
        Filmes filme = plataforma.getFilme(titulo, ano);
        if (filme == null) { System.out.println("Filme nao encontrado."); return; }
        plataforma.removerFilme(filme);
        System.out.println("Filme removido.");
    }

    /**
     * Associa um ator existente a um filme registado na plataforma.
     */
    /**
     * Associa um ator existente a um filme registado na plataforma.
     */
    private void associarAtorFilme() {
        if (plataforma.getFilmes().isEmpty() || plataforma.getAtores().isEmpty()) {
            System.out.println("Certifica-te de que existem filmes e atores registados na plataforma.");
            return;
        }

        // ── SELEÇÃO DO FILME ──
        System.out.println("\n── Escolhe o Filme ──");
        System.out.println("1. 🔍 Pesquisar filme por título...");
        for (int i = 0; i < plataforma.getFilmes().size(); i++) {
            Filmes f = plataforma.getFilmes().get(i);
            System.out.println((i + 2) + ". " + f.getTitulo() + " (" + f.getAno() + ")");
        }
        System.out.println("0. Cancelar");
        System.out.print("Opção: ");
        int escolhaFilme = lerInt(0, plataforma.getFilmes().size() + 1);
        if (escolhaFilme == 0) return;

        Filmes filmeEscolhido = null;
        if (escolhaFilme == 1) {
            System.out.print("\nEscreve parte do título do filme: ");
            String pesquisa = sc.nextLine().toLowerCase();
            ArrayList<Filmes> filtrados = plataforma.pesquisarFilmes(pesquisa);
            if (filtrados.isEmpty()) {
                System.out.println("Nenhum filme encontrado.");
                return;
            }
            for (int i = 0; i < filtrados.size(); i++) {
                System.out.println((i + 1) + ". " + filtrados.get(i).getTitulo() + " (" + filtrados.get(i).getAno() + ")");
            }
            System.out.println("0. Cancelar");
            System.out.print("Opção: ");
            int esc = lerInt(0, filtrados.size());
            if (esc == 0) return;
            filmeEscolhido = filtrados.get(esc - 1);
        } else {
            filmeEscolhido = plataforma.getFilmes().get(escolhaFilme - 2);
        }

        // ── SELEÇÃO DO ATOR ──
        System.out.println("\n── Escolhe o Ator para associar a '" + filmeEscolhido.getTitulo() + "' ──");
        System.out.println("1. 🔍 Pesquisar ator por nome...");
        for (int i = 0; i < plataforma.getAtores().size(); i++) {
            Atores a = plataforma.getAtores().get(i);
            System.out.println((i + 2) + ". " + a.getNome());
        }
        System.out.println("0. Cancelar");
        System.out.print("Opção: ");
        int escolhaAtor = lerInt(0, plataforma.getAtores().size() + 1);
        if (escolhaAtor == 0) return;

        Atores atorEscolhido = null;
        if (escolhaAtor == 1) {
            System.out.print("\nEscreve parte do nome do ator: ");
            String pesquisa = sc.nextLine().toLowerCase();
            ArrayList<Atores> filtrados = plataforma.pesquisarAtores(pesquisa);
            if (filtrados.isEmpty()) {
                System.out.println("Nenhum ator encontrado.");
                return;
            }
            for (int i = 0; i < filtrados.size(); i++) {
                System.out.println((i + 1) + ". " + filtrados.get(i).getNome());
            }
            System.out.println("0. Cancelar");
            System.out.print("Opção: ");
            int esc = lerInt(0, filtrados.size());
            if (esc == 0) return;
            atorEscolhido = filtrados.get(esc - 1);
        } else {
            atorEscolhido = plataforma.getAtores().get(escolhaAtor - 2);
        }

        if (filmeEscolhido.getAtores().contains(atorEscolhido)) {
            System.out.println("Aviso: O ator " + atorEscolhido.getNome() + " já está associado a este filme.");
        } else {
            filmeEscolhido.adicionarAtor(atorEscolhido);
            System.out.println("Sucesso: O ator " + atorEscolhido.getNome() + " foi associado com sucesso!");
        }
    }

    // ── Series ────────────────────────────────────────────────────────────────

    /**
     * Apresenta o sub-menu de gestão de séries.
     */
    private void menuSeries() {
        int opcao;
        do {
            System.out.println("\n── SERIES ──");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Remover");
            System.out.println("4. Adicionar Temporada");
            System.out.println("5. Adicionar Episodio");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt(0, 5);
            switch (opcao) {
                case 1: adicionarSerie();     break;
                case 2: listarSeries();       break;
                case 3: removerSerie();       break;
                case 4: adicionarTemporada(); break;
                case 5: adicionarEpisodio();  break;
            }
        } while (opcao != 0);
    }

    /**
     * Adiciona uma nova série à plataforma.
     */
    private void adicionarSerie() {
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.print("Genero: ");
        String genero = sc.nextLine();
        System.out.print("Ano: ");
        int ano = lerInt(1900, 2100);
        System.out.print("Duracao media (min): ");
        double duracao = lerInt(1, 300);
        try {
            plataforma.adicionarSerie(new Serie(titulo, parseGenero(genero), ano, duracao));
            System.out.println("Serie adicionada.");
        } catch (RecursoDuplicado e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Lista todas as séries da plataforma, permitindo navegar para as temporadas e episódios.
     */
    private void listarSeries() {
        if (plataforma.getSeries().isEmpty()) {
            System.out.println("Sem séries na plataforma.");
            return;
        }

        System.out.println("\n── Lista de Séries ──");
        for (int i = 0; i < plataforma.getSeries().size(); i++) {
            System.out.println((i + 1) + ". " + plataforma.getSeries().get(i).toString());
        }

        System.out.println("0. Voltar ao menu anterior");
        System.out.print("\nQueres ver os episódios de alguma série? (Escolhe o número): ");

        int escolha = lerInt(0, plataforma.getSeries().size());
        if (escolha == 0) return;

        Serie serieEscolhida = plataforma.getSeries().get(escolha - 1);
        verEpisodiosDe(serieEscolhida);
    }

    /**
     * Mostra os episódios de uma série, organizados por temporadas.
     * * @param serie A série da qual pretendemos visualizar os episódios.
     */
    private void verEpisodiosDe(Serie serie) {
        System.out.println("\n── Temporadas e Episódios: " + serie.getTitulo() + " ──");

        if (serie.getTemporadas().isEmpty()) {
            System.out.println("  (Ainda não existem temporadas registadas para esta série.)");
        } else {
            for (Temporada t : serie.getTemporadas()) {
                System.out.println("\n  ▶ Temporada " + t.getNumero() + " (" + t.getAno() + ")");

                if (t.getEpisodios().isEmpty()) {
                    System.out.println("    (Sem episódios nesta temporada)");
                } else {
                    for (Episodio e : t.getEpisodios()) {
                        System.out.println("    - Ep. " + e.getNumero() + ": " + e.getTitulo() + " (" + e.getDuracao() + " min)");
                    }
                }
            }
        }
        System.out.println("\n(Pressiona ENTER para voltar)");
        sc.nextLine();
    }

    /**
     * Remove uma série da plataforma identificada pelo título e ano.
     */
    private void removerSerie() {
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.print("Ano: ");
        int ano = lerInt(1900, 2100);
        Serie serie = plataforma.getSerie(titulo, ano);
        if (serie == null) { System.out.println("Serie nao encontrada."); return; }
        plataforma.removerSerie(serie);
        System.out.println("Serie removida.");
    }

    /**
     * Adiciona uma temporada a uma série existente.
     */
    private void adicionarTemporada() {
        System.out.print("Titulo da serie: ");
        String titulo = sc.nextLine();
        System.out.print("Ano da serie: ");
        int ano = lerInt(1900, 2100);
        Serie serie = plataforma.getSerie(titulo, ano);
        if (serie == null) { System.out.println("Serie nao encontrada."); return; }
        System.out.print("Numero da temporada: ");
        int num = lerInt(1, 100);
        System.out.print("Ano da temporada: ");
        int anoT = lerInt(1900, 2100);
        serie.adicionarTemporada(new Temporada(num, anoT));
        System.out.println("Temporada adicionada.");
    }

    /**
     * Adiciona um episódio a uma temporada de uma série específica.
     */
    /**
     * Adiciona um episódio a uma temporada de uma série específica.
     */
    private void adicionarEpisodio() {
        System.out.print("Titulo da serie: ");
        String titulo = sc.nextLine();
        System.out.print("Ano da serie: ");
        int ano = lerInt(1900, 2100);
        Serie serie = plataforma.getSerie(titulo, ano);
        if (serie == null) { System.out.println("Serie nao encontrada."); return; }

        System.out.print("Numero da temporada: ");
        int numTemporada = lerInt(1, 100);
        Temporada temporada = serie.getTemporada(numTemporada);
        if (temporada == null) { System.out.println("Temporada nao encontrada."); return; }

        System.out.print("Numero do episodio: ");
        int numE = lerInt(1, 100);
        System.out.print("Titulo do episodio: ");
        String tituloE = sc.nextLine();
        System.out.print("Duracao (min): ");
        double duracao = lerInt(1, 300);

        Episodio novoEpisodio = new Episodio(numE, tituloE, duracao);

        System.out.println("\n[Aviso] Um episodio tem de ter pelo menos 1 ator associado.");
        System.out.println("1. 🔍 Pesquisar ator por nome...");

        int totalAtores = plataforma.getAtores().size();
        for (int i = 0; i < totalAtores; i++) {
            Atores a = plataforma.getAtores().get(i);
            System.out.println((i + 2) + ". " + a.getNome());
        }

        int opcaoCriarNovo = totalAtores + 2;
        System.out.println(opcaoCriarNovo + ". ➕ Criar novo ator");
        System.out.println("0. Cancelar");
        System.out.print("Escolhe o número do ator principal: ");
        int escolhaAtor = lerInt(0, opcaoCriarNovo);
        if (escolhaAtor == 0) return;

        Atores atorEscolhido = null;

        if (escolhaAtor == 1) {
            System.out.print("\nEscreve parte do nome do ator: ");
            String pesquisa = sc.nextLine().toLowerCase();
            ArrayList<Atores> filtrados = plataforma.pesquisarAtores(pesquisa);
            if (filtrados.isEmpty()) {
                System.out.println("Nenhum ator encontrado.");
                return;
            }
            for (int i = 0; i < filtrados.size(); i++) {
                System.out.println((i + 1) + ". " + filtrados.get(i).getNome());
            }
            System.out.println("0. Cancelar");
            System.out.print("Opção: ");
            int esc = lerInt(0, filtrados.size());
            if (esc == 0) return;
            atorEscolhido = filtrados.get(esc - 1);

        } else if (escolhaAtor == opcaoCriarNovo) {
            System.out.println("\n── CRIAR NOVO ATOR ──");
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Data de nascimento (AAAA-MM-DD): ");
            String data = sc.nextLine();
            System.out.print("Nacionalidade: ");
            String nac = sc.nextLine();

            atorEscolhido = new Atores(nome, data, nac);
            plataforma.adicionarAtor(atorEscolhido);
            System.out.println("Novo ator criado!");
        } else {
            atorEscolhido = plataforma.getAtores().get(escolhaAtor - 2);
        }

        novoEpisodio.adicionarAtores(atorEscolhido);

        try {
            temporada.adicionarEpisodio(novoEpisodio);
            System.out.println("Episodio adicionado com sucesso.");
        } catch (AtorNaoAssociadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // ── Atores ────────────────────────────────────────────────────────────────

    /**
     * Apresenta o sub-menu para a gestão de atores na plataforma.
     */
    private void menuAtores() {
        int opcao;
        do {
            System.out.println("\n── ATORES ──");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Remover");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt(0, 3);
            switch (opcao) {
                case 1: adicionarAtor(); break;
                case 2: listarAtores();  break;
                case 3: removerAtor();   break;
            }
        } while (opcao != 0);
    }

    /**
     * Cria e adiciona um novo ator à plataforma.
     */
    private void adicionarAtor() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Data de nascimento: ");
        String data = sc.nextLine();
        System.out.print("Nacionalidade: ");
        String nac = sc.nextLine();
        plataforma.adicionarAtor(new Atores(nome, data, nac));
        System.out.println("Ator adicionado.");
    }

    /**
     * Lista todos os atores presentes na base de dados do sistema.
     */
    /**
     * Lista todos os atores presentes na base de dados do sistema e permite
     * escolher um para ver a sua filmografia (filmes e episódios onde entra).
     */
    private void listarAtores() {
        if (plataforma.getAtores().isEmpty()) {
            System.out.println("Sem atores na plataforma.");
            return;
        }

        System.out.println("\n── Lista de Atores ──");
        // Imprime os atores numerados
        for (int i = 0; i < plataforma.getAtores().size(); i++) {
            System.out.println((i + 1) + ". " + plataforma.getAtores().get(i).toString());
        }

        System.out.println("0. Voltar ao menu anterior");
        System.out.print("\nQueres ver a filmografia de algum ator? (Escolhe o número): ");

        int escolha = lerInt(0, plataforma.getAtores().size());
        if (escolha == 0) return;

        // Vai buscar o ator escolhido e mostra os seus trabalhos
        Atores atorEscolhido = plataforma.getAtores().get(escolha - 1);
        verTrabalhosDe(atorEscolhido);
    }

    /**
     * Pesquisa em todo o catálogo (Filmes e Séries) pelas participações de um ator específico.
     * @param ator O ator cuja filmografia pretendemos consultar.
     */
    private void verTrabalhosDe(Atores ator) {
        System.out.println("\n── Filmografia de: " + ator.getNome() + " ──");
        boolean encontrouAlgo = false;

        // 1. Procurar na lista de Filmes
        System.out.println("🎬 Filmes:");
        boolean temFilmes = false;
        for (Filmes f : plataforma.getFilmes()) {
            if (f.getAtores().contains(ator)) {
                System.out.println("  - " + f.getTitulo() + " (" + f.getAno() + ")");
                temFilmes = true;
                encontrouAlgo = true;
            }
        }
        if (!temFilmes) System.out.println("  (Nenhum filme registado)");

        // 2. Procurar na lista de Séries (dentro das temporadas e episódios)
        System.out.println("\n📺 Séries (Episódios):");
        boolean temSeries = false;
        for (Serie s : plataforma.getSeries()) {
            boolean participouNaSerie = false;

            for (Temporada t : s.getTemporadas()) {
                for (Episodio e : t.getEpisodios()) {
                    // Verifica se o ator está na lista de atores DESTE episódio
                    if (e.getAtores().contains(ator)) {
                        // Imprime o nome da série apenas na primeira vez que encontra um episódio
                        if (!participouNaSerie) {
                            System.out.println("  - " + s.getTitulo() + " (" + s.getAno() + "):");
                            participouNaSerie = true;
                            temSeries = true;
                            encontrouAlgo = true;
                        }
                        System.out.println("      -> T" + t.getNumero() + "E" + e.getNumero() + ": " + e.getTitulo());
                    }
                }
            }
        }
        if (!temSeries) System.out.println("  (Nenhuma participação em episódios de séries)");

        if (!encontrouAlgo) {
            System.out.println("\nEste ator ainda não está associado a nenhuma produção na plataforma.");
        }

        // Pausa pequena para o utilizador ler antes de o menu saltar novamente
        System.out.println("\n(Pressiona ENTER para voltar)");
        sc.nextLine(); // Consome o input vazio
    }

    /**
     * Remove um ator da base de dados com base no seu nome.
     */
    private void removerAtor() {
        System.out.print("Nome: ");
        Atores ator = plataforma.getAtor(sc.nextLine());
        if (ator == null) { System.out.println("Ator nao encontrado."); return; }
        plataforma.removerAtor(ator);
        System.out.println("Ator removido.");
    }

    // ── Utilizadores ─────────────────────────────────────────────────────────

    /**
     * Menu para a criação de diferentes perfis de utilizadores (via painel de Admin).
     */
    private void menuUtilizadores() {
        int opcao;
        do {
            System.out.println("\n── UTILIZADORES ──");
            System.out.println("1. Adicionar Utilizador");
            System.out.println("2. Adicionar Administrador");
            System.out.println("3. Listar");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt(0, 3);
            switch (opcao) {
                case 1: adicionarUtilizador();    break;
                case 2: adicionarAdministrador(); break;
                case 3: listarUtilizadores();     break;
            }
        } while (opcao != 0);
    }

    /**
     * Adiciona um utilizador padrão à plataforma.
     */
    private void adicionarUtilizador() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        plataforma.adicionarUtilizador(new UtilizadorRegistrado(username, email, password));
        System.out.println("Utilizador adicionado.");
    }

    /**
     * Adiciona um utilizador administrador com privilégios totais.
     */
    private void adicionarAdministrador() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        plataforma.adicionarUtilizador(new Administrador(username, email, password));
        System.out.println("Administrador adicionado.");
    }

    /**
     * Lista as informações de todos os utilizadores (registados e administradores) no sistema.
     */
    private void listarUtilizadores() {
        if (plataforma.getUtilizadores().isEmpty()) {
            System.out.println("Sem utilizadores.");
            return;
        }
        for (Utilizador u : plataforma.getUtilizadores())
            System.out.println("  " + u);
    }

    // ── Pesquisa ──────────────────────────────────────────────────────────────

    /**
     * Permite a um novo utilizador criar a sua própria conta diretamente no menu principal.
     */
    private void registarUtilizador() {
        System.out.println("\n── CRIAR NOVA CONTA ──");
        System.out.print("Escolhe o teu Username: ");
        String username = sc.nextLine();

        if (plataforma.getUtilizador(username) != null) {
            System.out.println("Erro: Já existe um utilizador com esse nome. Tenta outro username.");
            return;
        }

        System.out.print("Introduz o teu Email: ");
        String email = sc.nextLine();

        System.out.print("Escolhe uma Password: ");
        String password = sc.nextLine();

        UtilizadorRegistrado novoUser = new UtilizadorRegistrado(username, email, password);
        plataforma.adicionarUtilizador(novoUser);

        System.out.println("Sucesso: A tua conta foi criada! Já podes usar a opção 6 para entrar.");
    }

    /**
     * Menu para realizar pesquisas globais usando a funcionalidade de "Pesquisavel".
     */
    private void menuPesquisa() {
        int opcao;
        do {
            System.out.println("\n── PESQUISA ──");
            System.out.println("1. Filmes");
            System.out.println("2. Series");
            System.out.println("3. Atores");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt(0, 3);
            switch (opcao) {
                case 1:
                    System.out.print("Titulo: ");
                    for (Filmes f : plataforma.pesquisarFilmes(sc.nextLine()))
                        System.out.println("  " + f);
                    break;
                case 2:
                    System.out.print("Titulo: ");
                    for (Serie s : plataforma.pesquisarSeries(sc.nextLine()))
                        System.out.println("  " + s);
                    break;
                case 3:
                    System.out.print("Nome: ");
                    for (Atores a : plataforma.pesquisarAtores(sc.nextLine()))
                        System.out.println("  " + a);
                    break;
            }
        } while (opcao != 0);
    }

    // ── Listagens ─────────────────────────────────────────────────────────────

    /**
     * Apresenta as diferentes listagens e rankings da plataforma, baseadas em algoritmos de ordenação.
     */
    private void menuListagens() {
        int opcao;
        do {
            System.out.println("\n── RANKINGS E LISTAGENS ──");
            System.out.println("1. Filmes por Titulo");
            System.out.println("2. Filmes por Classificacao Média");
            System.out.println("3. Atores por Nome");
            System.out.println("4. Atores por Número de Filmes/Séries");
            System.out.println("5. Utilizadores com mais filmes vistos");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt(0, 5);

            switch (opcao) {
                case 1:
                    System.out.println("\n[ Filmes Ordenados por Título ]");
                    for (Filmes f : plataforma.filmesOrdenadosPorTitulo())
                        System.out.println("  🎬 " + f.getTitulo() + " (" + f.getAno() + ")");
                    break;
                case 2:
                    System.out.println("\n[ Filmes Ordenados por Classificação ]");
                    for (Filmes f : plataforma.filmesOrdenadosPorClassificacao())
                        System.out.println("  ⭐ " + f.getTitulo() + " | Nota: " + String.format("%.1f", f.calcularClassificacaoMedia()));
                    break;
                case 3:
                    System.out.println("\n[ Atores Ordenados por Nome ]");
                    for (Atores a : plataforma.atoresOrdenadosPorNome())
                        System.out.println("  🎭 " + a.getNome());
                    break;
                case 4:
                    System.out.println("\n[ Atores com Mais Participações ]");
                    for (Atores a : plataforma.atoresOrdenadosPorParticipacoes())
                        System.out.println("  🎭 " + a.getNome() + " | Entrou em: " + plataforma.contarParticipacoes(a) + " produções");
                    break;
                case 5:
                    System.out.println("\n[ Utilizadores Mais Ativos ]");
                    for (UtilizadorRegistrado ur : plataforma.utilizadoresComMaisFilmesVistos())
                        System.out.println("  👤 " + ur.getUsername() + " | Filmes Vistos: " + ur.totalFilmesVistos());
                    break;
            }
        } while (opcao != 0);
    }

    // ── Interacoes ────────────────────────────────────────────────────────────

    /**
     * Interface privada onde o utilizador registado pode interagir com o conteúdo da plataforma
     * (dar classificações, adicionar aos favoritos, ver histórico, etc).
     */
    private void menuInteracoes() {
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Utilizador u = plataforma.getUtilizador(username);

        if (u == null) {
            System.out.println("Utilizador nao encontrado.");
            return;
        }

        if (u.getPassword().equals(password) == false) {
            System.out.println("Erro: Password incorreta.");
            return;
        }

        if (!(u instanceof UtilizadorRegistrado)) {
            System.out.println("Apenas utilizadores registados podem interagir.");
            return;
        }
        UtilizadorRegistrado user = (UtilizadorRegistrado) u;

        int opcao;
        do {
            System.out.println("\n── INTERACOES [" + user.getUsername() + "] ──");
            System.out.println("1. Marcar Filme como Visto");
            System.out.println("2. Adicionar Filme a Watchlist");
            System.out.println("3. Remover Filme da Watchlist");
            System.out.println("4. Ver Watchlist");
            System.out.println("5. Classificar Filme");
            System.out.println("6. Comentar Filme");
            System.out.println("7. Adicionar Filme a Favoritos");
            System.out.println("8. Adicionar Serie a Favoritos");
            System.out.println("9. Ver Favoritos");
            System.out.println("10. Ver Histórico de Vistos");
            System.out.println("11. Remover Filme dos Vistos");
            System.out.println("12. Ver as Minhas Classificações");
            System.out.println("0. Voltar");
            System.out.print("Opcao: ");
            opcao = lerInt(0, 12);
            switch (opcao) {
                case 1: marcarFilmeVisto(user);          break;
                case 2: adicionarFilmeWatchlist(user);   break;
                case 3: removerFilmeWatchlist(user);     break;
                case 4: verWatchlist(user);              break;
                case 5: classificarFilme(user);          break;
                case 6: comentarFilme(user);             break;
                case 7: adicionarFilmeFavorito(user);    break;
                case 8: adicionarSerieFavorita(user);    break;
                case 9: verFavoritos(user);              break;
                case 10: verItensVistos(user);           break;
                case 11: removerFilmeVisto(user);        break;
                case 12: verMinhasClassificacoes(user);  break;
            }
        } while (opcao != 0);
    }

    /**
     * Marca um filme específico como visto no histórico do utilizador autenticado.
     * * @param user Utilizador registado com sessão iniciada.
     */
    /**
     * Marca um filme específico como visto no histórico do utilizador autenticado.
     */
    private void marcarFilmeVisto(UtilizadorRegistrado user) {
        if (plataforma.getFilmes().isEmpty()) {
            System.out.println("Não existem filmes na plataforma.");
            return;
        }

        System.out.println("\n── Escolhe o filme ──");
        System.out.println("1. 🔍 Pesquisar filme por título...");
        for (int i = 0; i < plataforma.getFilmes().size(); i++) {
            Filmes f = plataforma.getFilmes().get(i);
            String estado = user.jaViuFilme(f) ? "[👀 Visto]" : "[  Por ver  ]";
            System.out.println((i + 2) + ". " + estado + " " + f.getTitulo() + " (" + f.getAno() + ")");
        }
        System.out.println("0. Cancelar");
        System.out.print("Opção: ");

        int escolha = lerInt(0, plataforma.getFilmes().size() + 1);
        if (escolha == 0) return;

        Filmes filmeEscolhido = null;

        if (escolha == 1) {
            System.out.print("\nEscreve parte do título do filme: ");
            String pesquisa = sc.nextLine().toLowerCase();
            ArrayList<Filmes> filtrados = plataforma.pesquisarFilmes(pesquisa);

            if (filtrados.isEmpty()) {
                System.out.println("Nenhum filme encontrado.");
                return;
            }

            System.out.println("\n── Resultados da Pesquisa ──");
            for (int i = 0; i < filtrados.size(); i++) {
                String estado = user.jaViuFilme(filtrados.get(i)) ? "[👀 Visto]" : "[  Por ver  ]";
                System.out.println((i + 1) + ". " + estado + " " + filtrados.get(i).getTitulo() + " (" + filtrados.get(i).getAno() + ")");
            }
            System.out.println("0. Cancelar");
            System.out.print("Opção: ");
            int esc = lerInt(0, filtrados.size());
            if (esc == 0) return;
            filmeEscolhido = filtrados.get(esc - 1);
        } else {
            filmeEscolhido = plataforma.getFilmes().get(escolha - 2);
        }

        if (user.jaViuFilme(filmeEscolhido)) {
            System.out.println("Já tinhas marcado o '" + filmeEscolhido.getTitulo() + "' como visto!");
        } else {
            user.marcarFilmeComoVisto(filmeEscolhido);
            System.out.println("Marcaste o filme '" + filmeEscolhido.getTitulo() + "' como visto.");
        }
    }

    /**
     * Apresenta no ecrã o histórico dos filmes e episódios vistos pelo utilizador.
     * * @param user Utilizador registado com sessão iniciada.
     */
    private void verItensVistos(UtilizadorRegistrado user) {
        System.out.println("── Histórico de Conteúdo Visto por " + user.getUsername() + " ──");

        if (user.getFilmesVistos().isEmpty() && user.getEpisodiosVistos().isEmpty()) {
            System.out.println("  (ainda não viste nenhum filme ou episódio)");
            return;
        }

        if (!user.getFilmesVistos().isEmpty()) {
            System.out.println("🎥 Filmes Vistos:");
            for (Filmes f : user.getFilmesVistos()) {
                System.out.println("  - " + f.getTitulo() + " (" + f.getAno() + ")");
            }
        }

        if (!user.getEpisodiosVistos().isEmpty()) {
            System.out.println("\n📺 Episódios Vistos:");
            for (Episodio e : user.getEpisodiosVistos()) {
                System.out.println("  - " + e.getTitulo());
            }
        }
    }

    /**
     * Permite a um utilizador procurar e adicionar um filme à sua Watchlist (lista de ver mais tarde).
     * * @param user Utilizador registado com sessão iniciada.
     */
    private void adicionarFilmeWatchlist(UtilizadorRegistrado user) {
        if (plataforma.getFilmes().isEmpty()) {
            System.out.println("Não existem filmes na plataforma.");
            return;
        }

        System.out.println("\n── Adicionar à Watchlist ──");
        System.out.println("1. 🔍 Pesquisar no catálogo...");
        System.out.println("--------------------------------");

        for (int i = 0; i < plataforma.getFilmes().size(); i++) {
            Filmes f = plataforma.getFilmes().get(i);
            System.out.println((i + 2) + ". " + f.getTitulo() + " (" + f.getAno() + ")");
        }
        System.out.println("0. Cancelar");
        System.out.print("Opção: ");

        int escolha = lerInt(0, plataforma.getFilmes().size() + 1);

        if (escolha == 0) {
            return;
        }

        Filmes filmeEscolhido = null;

        if (escolha == 1) {
            System.out.print("\nEscreve parte do título: ");
            String pesquisa = sc.nextLine().toLowerCase();

            ArrayList<Filmes> filmesFiltrados = new ArrayList<>();
            for (Filmes f : plataforma.getFilmes()) {
                if (f.getTitulo().toLowerCase().contains(pesquisa)) {
                    filmesFiltrados.add(f);
                }
            }

            if (filmesFiltrados.isEmpty()) {
                System.out.println("Nenhum filme encontrado no catálogo com essa pesquisa.");
                return;
            }

            System.out.println("\n── Resultados da Pesquisa ──");
            for (int i = 0; i < filmesFiltrados.size(); i++) {
                Filmes f = filmesFiltrados.get(i);
                System.out.println((i + 1) + ". " + f.getTitulo() + " (" + f.getAno() + ")");
            }
            System.out.println("0. Cancelar");
            System.out.print("Opção: ");

            int escolhaPesquisa = lerInt(0, filmesFiltrados.size());
            if (escolhaPesquisa == 0) return;

            filmeEscolhido = filmesFiltrados.get(escolhaPesquisa - 1);

        } else {
            filmeEscolhido = plataforma.getFilmes().get(escolha - 2);
        }

        if (user.getWatchlist().getFilmes().contains(filmeEscolhido)) {
            System.out.println("Aviso: O filme '" + filmeEscolhido.getTitulo() + "' já está na tua watchlist!");
        } else {
            user.adicionarFilmeLista(filmeEscolhido);
            System.out.println("Sucesso: O filme '" + filmeEscolhido.getTitulo() + "' foi adicionado à tua watchlist.");
        }
    }

    /**
     * Remove um filme da Watchlist pessoal do utilizador autenticado.
     * * @param user Utilizador registado.
     */
    private void removerFilmeWatchlist(UtilizadorRegistrado user) {
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.print("Ano: ");
        int ano = lerInt(1900, 2100);
        Filmes filme = plataforma.getFilme(titulo, ano);
        if (filme == null) { System.out.println("Filme nao encontrado."); return; }
        boolean removido = user.removerFilmeLista(filme);
        System.out.println(removido ? "Removido da watchlist." : "Filme nao estava na watchlist.");
    }

    /**
     * Apresenta a Watchlist pessoal do utilizador no ecrã.
     * * @param user Utilizador cujos dados vão ser lidos.
     */
    private void verWatchlist(UtilizadorRegistrado user) {
        System.out.println("── Watchlist de " + user.getUsername() + " ──");
        if (user.getWatchlist().getFilmes().isEmpty() &&
                user.getWatchlist().getEpisodios().isEmpty()) {
            System.out.println("  (vazia)");
            return;
        }
        for (Filmes f : user.getWatchlist().getFilmes())
            System.out.println("  Filme: " + f.getTitulo() + " (" + f.getAno() + ")");
        for (Episodio e : user.getWatchlist().getEpisodios())
            System.out.println("  Episodio: " + e.getTitulo());
    }

    /**
     * Permite a um utilizador dar uma nota (1 a 10) a um filme.
     * Restringe a atribuição da classificação apenas a filmes marcados como vistos.
     * * @param user O utilizador que pretende classificar o filme.
     */
    private void classificarFilme(UtilizadorRegistrado user) {
        if (user.getFilmesVistos().isEmpty()) {
            System.out.println("Ainda não tens nenhum filme no teu histórico de vistos.");
            System.out.println("Só podes classificar filmes que já tenhas visto!");
            return;
        }

        System.out.println("\n── Classificar Filme ──");
        System.out.println("1. 🔍 Pesquisar nos teus vistos...");
        System.out.println("--------------------------------");

        for (int i = 0; i < user.getFilmesVistos().size(); i++) {
            Filmes f = user.getFilmesVistos().get(i);
            System.out.println((i + 2) + ". " + f.getTitulo() + " (" + f.getAno() + ")");
        }
        System.out.println("0. Cancelar");
        System.out.print("Opção: ");

        int escolha = lerInt(0, user.getFilmesVistos().size() + 1);

        if (escolha == 0) {
            return;
        }

        Filmes filmeEscolhido = null;

        if (escolha == 1) {

            System.out.print("\nInsira o título: ");
            String pesquisa = sc.nextLine().toLowerCase();

            ArrayList<Filmes> vistosFiltrados = new ArrayList<>();
            for (Filmes f : user.getFilmesVistos()) {
                if (f.getTitulo().toLowerCase().contains(pesquisa)) {
                    vistosFiltrados.add(f);
                }
            }

            if (vistosFiltrados.isEmpty()) {
                System.out.println("Nenhum filme encontrado nos teus vistos com essa pesquisa.");
                return;
            }

            System.out.println("\n── Resultados da Pesquisa ──");
            for (int i = 0; i < vistosFiltrados.size(); i++) {
                Filmes f = vistosFiltrados.get(i);
                System.out.println((i + 1) + ". " + f.getTitulo() + " (" + f.getAno() + ")");
            }
            System.out.println("0. Cancelar");
            System.out.print("Opção: ");

            int escolhaPesquisa = lerInt(0, vistosFiltrados.size());
            if (escolhaPesquisa == 0) return;

            filmeEscolhido = vistosFiltrados.get(escolhaPesquisa - 1);

        } else {
            filmeEscolhido = user.getFilmesVistos().get(escolha - 2);
        }

        System.out.print("Introduz a tua classificação (1 a 10): ");
        int nota = lerInt(1, 10);

        try {
            user.classificarFilme(filmeEscolhido, nota);
            System.out.println("Sucesso: Atribuíste a nota " + nota + "/10 ao filme '" + filmeEscolhido.getTitulo() + "'!");
        } catch (NaoVistoException | JaClassificouException | ClassificacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Associa um comentário textual a um determinado filme usando os dados do utilizador.
     * * @param user O utilizador que cria o comentário.
     */
    /**
     * Associa um comentário textual a um determinado filme usando os dados do utilizador.
     * Só permite a inserção do comentário se o utilizador já tiver classificado o filme previamente.
     */
    private void comentarFilme(UtilizadorRegistrado user) {
        if (plataforma.getFilmes().isEmpty()) {
            System.out.println("Não existem filmes na plataforma.");
            return;
        }

        System.out.println("\n── Comentar Filme ──");
        System.out.println("1. 🔍 Pesquisar filme por título...");
        System.out.println("--------------------------------");

        for (int i = 0; i < plataforma.getFilmes().size(); i++) {
            Filmes f = plataforma.getFilmes().get(i);
            System.out.println((i + 2) + ". " + f.getTitulo() + " (" + f.getAno() + ")");
        }
        System.out.println("0. Cancelar");
        System.out.print("Opção: ");

        int escolha = lerInt(0, plataforma.getFilmes().size() + 1);

        if (escolha == 0) {
            return;
        }

        Filmes filmeEscolhido = null;

        if (escolha == 1) {
            System.out.print("\nEscreve parte do título do filme: ");
            String pesquisa = sc.nextLine().toLowerCase();

            ArrayList<Filmes> filmesFiltrados = plataforma.pesquisarFilmes(pesquisa);

            if (filmesFiltrados.isEmpty()) {
                System.out.println("Nenhum filme encontrado no catálogo com essa pesquisa.");
                return;
            }

            System.out.println("\n── Resultados da Pesquisa ──");
            for (int i = 0; i < filmesFiltrados.size(); i++) {
                Filmes f = filmesFiltrados.get(i);
                System.out.println((i + 1) + ". " + f.getTitulo() + " (" + f.getAno() + ")");
            }
            System.out.println("0. Cancelar");
            System.out.print("Opção: ");

            int escolhaPesquisa = lerInt(0, filmesFiltrados.size());
            if (escolhaPesquisa == 0) return;

            filmeEscolhido = filmesFiltrados.get(escolhaPesquisa - 1);

        } else {
            filmeEscolhido = plataforma.getFilmes().get(escolha - 2);
        }

        // ── NOVA REGRA DE NEGÓCIO: Verificar se o utilizador já classificou o filme ──
        boolean jaClassificou = false;
        for (Classificacao c : filmeEscolhido.getClassificacoes()) {
            if (c.getUsernameUtilizador().equals(user.getUsername())) {
                jaClassificou = true;
                break; // Encontrou a nota do utilizador, pode parar de procurar
            }
        }

        if (jaClassificou == false) {
            System.out.println("\n⚠️ Erro: Não podes comentar o '" + filmeEscolhido.getTitulo() + "'.");
            System.out.println("Regra da plataforma: Primeiro tens de classificar o filme (dar uma nota) antes de deixares um comentário.");
            return; // Interrompe o processo e volta ao menu
        }
        // ─────────────────────────────────────────────────────────────────────────────

        System.out.print("Escreve o teu comentário: ");
        String textoComentario = sc.nextLine();

        String dataHoje = java.time.LocalDate.now().toString();

        user.comentarFilme(filmeEscolhido, textoComentario, dataHoje);
        System.out.println("Sucesso: O teu comentário foi adicionado ao filme '" + filmeEscolhido.getTitulo() + "'!");
    }

    /**
     * Adiciona um filme procurado à lista de favoritos do utilizador.
     * * @param user Utilizador registado responsável pela ação.
     */
    private void adicionarFilmeFavorito(UtilizadorRegistrado user) {
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.print("Ano: ");
        int ano = lerInt(1900, 2100);
        Filmes filme = plataforma.getFilme(titulo, ano);
        if (filme == null) { System.out.println("Filme nao encontrado."); return; }
        user.adicionarFilmeFavorito(filme);
        System.out.println("Filme adicionado aos favoritos.");
    }

    /**
     * Adiciona uma série procurada à lista de favoritos do utilizador.
     * * @param user Utilizador registado responsável pela ação.
     */
    private void adicionarSerieFavorita(UtilizadorRegistrado user) {
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.print("Ano: ");
        int ano = lerInt(1900, 2100);
        Serie serie = plataforma.getSerie(titulo, ano);
        if (serie == null) { System.out.println("Serie nao encontrada."); return; }
        user.adicionarSerieFavorita(serie);
        System.out.println("Serie adicionada aos favoritos.");
    }

    /**
     * Mostra os conteúdos marcados como favoritos pelo utilizador.
     * * @param user Utilizador que consulta os próprios favoritos.
     */
    private void verFavoritos(UtilizadorRegistrado user) {
        System.out.println("── Favoritos de " + user.getUsername() + " ──");
        if (user.getFavoritos().getFilmes().isEmpty() &&
                user.getFavoritos().getSeries().isEmpty()) {
            System.out.println("  (vazio)");
            return;
        }
        for (Filmes f : user.getFavoritos().getFilmes())
            System.out.println("  Filme: " + f.getTitulo() + " (" + f.getAno() + ")");
        for (Serie s : user.getFavoritos().getSeries())
            System.out.println("  Serie: " + s.getTitulo() + " (" + s.getAno() + ")");
    }

    /**
     * Permite apagar do histórico do utilizador um filme previamente registado como "Visto".
     * * @param user Utilizador que pretende limpar o seu histórico.
     */
    private void removerFilmeVisto(UtilizadorRegistrado user) {
        if (user.getFilmesVistos().isEmpty()) {
            System.out.println("Ainda não tens filmes marcados como vistos no teu histórico.");
            return;
        }

        System.out.println("\n── Escolhe o filme a remover dos Vistos ──");
        for (int i = 0; i < user.getFilmesVistos().size(); i++) {
            Filmes f = user.getFilmesVistos().get(i);
            System.out.println((i + 1) + ". X " + f.getTitulo() + " (" + f.getAno() + ")");
        }
        System.out.println("0. Cancelar");
        System.out.print("Opção: ");

        int escolha = lerInt(0, user.getFilmesVistos().size());
        if (escolha == 0) return;

        Filmes filmeEscolhido = user.getFilmesVistos().get(escolha - 1);

        user.removerFilmeDosVistos(filmeEscolhido);
        System.out.println("O filme '" + filmeEscolhido.getTitulo() + "' foi apagado do teu histórico!");
    }

    /**
     * Apresenta de forma estruturada as classificações que o utilizador já publicou.
     * * @param user Utilizador que quer ver as suas notas.
     */
    private void verMinhasClassificacoes(UtilizadorRegistrado user) {
        System.out.println("\n── As Minhas Classificações (" + user.getUsername() + ") ──");
        boolean encontrou = false;

        for (Filmes f : user.getFilmesVistos()) {
            for (Classificacao c : f.getClassificacoes()) {
                if (c.getUsernameUtilizador().equals(user.getUsername())) {
                    System.out.println("🎬 " + f.getTitulo() + " | ⭐ A tua nota: " + c.getValor() + "/10");
                    encontrou = true;
                }
            }
        }

        if (!encontrou) {
            System.out.println("  (Ainda não deste nota a nenhum filme)");
        }
    }

    // ── Utilitario ────────────────────────────────────────────────────────────

    /**
     * Método auxiliar de input que obriga o utilizador a inserir um valor inteiro dentro de um intervalo.
     * Capta erros de inserção caso sejam digitadas letras ou valores fora dos limites especificados.
     * * @param min Valor mínimo aceite pela função (inclusivo).
     * @param max Valor máximo aceite pela função (inclusivo).
     * @return O valor inteiro validado pela função.
     */
    private int lerInt(int min, int max) {
        int valor = -1;
        while (valor < min || valor > max) {
            try {
                valor = Integer.parseInt(sc.nextLine().trim());
                if (valor < min || valor > max)
                    System.out.print("Valor entre " + min + " e " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Introduza um numero: ");
            }
        }
        return valor;
    }

    /**
     * Conversor textual para o Enum de Genero.
     * Analisa uma string inserida no teclado e mapeia-a para o seu correspondente no enum Genero.
     * * @param texto A string de texto representando um possível género cinematográfico.
     * @return O Genero correspondente da classe Enum. Retorna Genero.DESCONHECIDO se não houver match.
     */
    private Genero parseGenero(String texto) {
        switch (texto.trim().toUpperCase().replace(" ", "_")) {
            case "ACAO":            return Genero.ACAO;
            case "COMEDIA":         return Genero.COMEDIA;
            case "DRAMA":           return Genero.DRAMA;
            case "FICAO_CIENTIFICA": return Genero.FICAO_CIENTIFICA;
            case "TERROR":          return Genero.TERROR;
            case "ROMANCE":         return Genero.ROMANCE;
            case "DOCUMENTARIO":    return Genero.DOCUMENTARIO;
            default:                return Genero.DESCONHECIDO;
        }
    }
}