package com.example.literalura.util;

import com.example.literalura.controller.BookApiClient;
import com.example.literalura.model.*;
import com.example.literalura.repository.LivroRepository;
import com.example.literalura.repository.AutorRepository;
import com.example.literalura.service.JsonConversor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final BookApiClient api;
    private final JsonConversor conversor;
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public Menu(BookApiClient api,
                JsonConversor conversor,
                LivroRepository livroRepository,
                AutorRepository autorRepository) {
        this.api = api;
        this.conversor = conversor;
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibirMenu() {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                ===== LITERALURA =====
                1 - Buscar livro por título
                2 - Listar livros salvos
                3 - Listar autores registrados
                4 - Listar autores vivos em determinado ano
                5 - Listar livros em determinado idioma
                0 - Sair
                """);

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> buscarLivro();
                case 2 -> listarLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivosPorAno();
                case 5 -> exibirEstatisticasPorIdioma();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarLivro() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();

        var livroExistente = livroRepository.findByTitleIgnoreCase(titulo);

        if (livroExistente.isPresent()) {
            System.out.println("Livro já cadastrado no banco:");
            System.out.println(livroExistente.get());

            System.out.println("Autor:");
            System.out.println(livroExistente.get().getAuthor());

            return;
        }

        String json = api.buscarLivroPorTitulo(titulo);

        GutendexResponse resposta =
                conversor.dateConversor(json, GutendexResponse.class);

        if (resposta.getResults() == null || resposta.getResults().isEmpty()) {
            System.out.println("Livro não encontrado.");
            return;
        }

        Book livro = resposta.getResults().get(0);

        salvarLivroComAutor(livro);

        System.out.println("Livro salvo com sucesso:");
        System.out.println(livro);

        System.out.println("Autor:");
        System.out.println(livro.getAuthor());
    }


    private void salvarLivroComAutor(Book livro) {
        var livroExistente = livroRepository.findByTitleIgnoreCase(livro.getTitle());
        if (livroExistente.isPresent()) {
            System.out.println("Livro já cadastrado no banco.");
            return;
        }

        List<AuthorApiDTO> autoresApi = livro.getAuthorsApi();
        if (autoresApi == null || autoresApi.isEmpty()) {
            System.out.println("Livro sem autor registrado.");
            return;
        }

        AuthorApiDTO autorApi = autoresApi.get(0);

        Author autor = autorRepository
                .findByNomeIgnoreCase(autorApi.getNome())
                .orElseGet(() -> {
                    Author novo = new Author();
                    novo.setNome(autorApi.getNome());
                    novo.setAnoNascimento(autorApi.getAnoNascimento());
                    novo.setAnoFalecimento(autorApi.getAnoFalecimento());
                    return autorRepository.save(novo);
                });

        livro.setAuthor(autor);
        livroRepository.save(livro);
    }

    private void listarLivros() {
        livroRepository.findAll().forEach(System.out::println);
    }

    private void listarAutores() {
        autorRepository.findAll().forEach(System.out::println);
    }

    private void listarAutoresVivosPorAno() {
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        List<Author> vivos = autorRepository
                .findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano);

        List<Author> vivosSemFalecimento = autorRepository
                .findByAnoNascimentoLessThanEqualAndAnoFalecimentoIsNull(ano);

        vivos.addAll(vivosSemFalecimento);

        if (vivos.isEmpty()) {
            System.out.println("Nenhum autor vivo nesse ano.");
        } else {
            vivos.forEach(System.out::println);
        }
    }

    private void exibirEstatisticasPorIdioma() {
        livroRepository.findAll()
                .stream()
                .flatMap(book -> book.getLanguages().stream())
                .collect(Collectors.groupingBy(
                        String::toUpperCase,
                        Collectors.counting()
                ))
                .forEach((idioma, qtd) ->
                        System.out.println("Idioma: " + idioma + " | Livros: " + qtd)
                );
    }


}
