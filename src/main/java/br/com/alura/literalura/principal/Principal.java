package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autores;
import br.com.alura.literalura.model.DadosResult;
import br.com.alura.literalura.model.Livros;
import br.com.alura.literalura.repository.LivroRespository;
import br.com.alura.literalura.service.ConsumiApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {


    private LivroRespository respository;

    private Scanner scanner = new Scanner(System.in);
    private ConsumiApi api = new ConsumiApi();
    private ConverteDados conversor = new ConverteDados();
    final String URL = "https://gutendex.com/books";
    final String SEARCH = "/?search=";

    public Principal(LivroRespository respository) {
        this.respository = respository;
    }

    public void exibirMenu() {


        var opcao = -1;
        while (opcao != 0) {
            System.out.println("""
                    ******************
                    Escolha o número de sua opção:
                    1 - Buscar Livro Pelo Título
                    2 - Listar Livros Registrados
                    3 - Listar Autores Registrados
                    4 - Listar Autores Vivos Em Um Determinado Ano
                    5 - Listar Livros Em Um Determinado Idioma
                    0 - Sair
                    ******************
                    
                    Digite a opção desejada:
                    """);

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o título do livro:");
                    var tituloLivro = scanner.nextLine();
                    var json = api.consumir(URL + SEARCH + tituloLivro.replace(" ", "+").toLowerCase());
                    var results = conversor.converter(json, DadosResult.class);

                    if (!results.results().isEmpty()) {
                        var livroRecord = results.results().getFirst();
                        var autoresListRecord = livroRecord.autores();

                        Livros livro = new Livros(livroRecord);
                        List<Autores> autores = autoresListRecord.stream()
                                .map(a -> new Autores(a.nome(), a.anoNascimento(), a.anoFalescimento()))
                                .toList();

                        autores.forEach(a -> a.setLivro(livro));
                        livro.setAutores(autores);
                        respository.save(livro);
                    } else System.out.println("Nenhum livro foi encontrado com o título inserido.");

                    break;
                case 2:
                    var livros = respository.findAll();
                    livros.forEach(System.out::println);
                    break;
                case 3:
                    List<Autores> autores = respository.obterAutores();
                    autores.forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Digite o ano para verificar os autores vivos:");
                    var ano = scanner.nextInt();
                    List<Autores> autoresVivos = respository.obterAutoresVivosAteOAno(ano);
                    autoresVivos.forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Digite o idioma que deseja pesquisar os livros:");
                    var idioma = scanner.nextLine();
                    List<Livros> livrosPorIdioma = respository.obterLivrosPorIdioma(idioma.toLowerCase());
                    if (livrosPorIdioma.isEmpty())
                        System.out.println("Não foram encontrados livros com o idioma inserido.");
                    else livrosPorIdioma.forEach(System.out::println);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção indisponível.");
                    break;
            }
        }
    }
}
