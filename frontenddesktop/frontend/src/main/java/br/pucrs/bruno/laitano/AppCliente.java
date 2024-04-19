package br.pucrs.bruno.laitano;

import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.*;

public class AppCliente {

    private static final String URL_SERVIDOR = "http://localhost:8080";
    private static final String ENDPOINT_LIVROS = "/biblioteca/livros";
    private static final String ENDPOINT_LIVROTITULO = "/biblioteca/livrotitulo/";
    private static final String ENDPOINT_NOVOLIVRO = "/biblioteca/novolivro";

    private Scanner entrada = new Scanner(System.in);

    public void executar() {
        consultarTodosLivros();
        cadastrarLivro();
        consultarTodosLivros();
        consultarLivroPorTitulo();
    }

    // Consulta todos os livros
    public void consultarTodosLivros() {
        System.out.println("==============================");
        System.out.println("Lista dos livros:");

        String urlRequisicao = URL_SERVIDOR + ENDPOINT_LIVROS;
        System.out.println("URL:" + urlRequisicao);

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder(URI.create(urlRequisicao))
                    .GET()
                    .build();
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            int statusCode = response.statusCode();
            System.out.println("HTTP status: " + statusCode);

            System.out.println("Livros da resposta: ");
            List<Livro> livros = LivroUtils.toList(response.body());
            livros.forEach(System.out::println);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Consulta um livro por titulo
    public void consultarLivroPorTitulo() {
        System.out.println("==============================");
        System.out.println("Livro de um titulo:");
        System.out.print("Digite o titulo do livro: ");
        String nomeLivro = entrada.nextLine();
        nomeLivro = LivroUtils.ajustaString(nomeLivro);
        System.out.println("Titulo ajustado: " + nomeLivro);

        String urlRequisicao = URL_SERVIDOR + ENDPOINT_LIVROTITULO + nomeLivro;
        System.out.println("URL:" + urlRequisicao);

        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder(URI.create(urlRequisicao))
                    .GET()
                    .build();

            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            int statusCode = response.statusCode();
            System.out.println("HTTP status: " + statusCode);

            System.out.println("Livro da resposta: ");
            Livro livro = LivroUtils.toObject(response.body());
            System.out.println(livro);

        } catch (Exception e) {
            System.out.println("Livro nao encontrado.");
        }
    }

    // Cadastra um livro
    public void cadastrarLivro() {
        Livro livro = new Livro(999, "Cliente REST", "Maria da Silva", 2024);

        System.out.println("==============================");
        System.out.println("Cadastro de um novo livro:");

        String urlRequisicao = URL_SERVIDOR + ENDPOINT_NOVOLIVRO;
        System.out.println("URL:" + urlRequisicao);

        try {
            HttpRequest.BodyPublisher livroPublisher = HttpRequest.BodyPublishers.ofString(LivroUtils.toJson(livro));

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder(URI.create(urlRequisicao))
                    .POST(livroPublisher)
                    .setHeader("Content-Type", "application/json")
                    .build();

            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

            int statusCode = response.statusCode();
            System.out.println("HTTP status: " + statusCode);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
