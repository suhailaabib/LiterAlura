package br.com.alura.LiterAlura.principal;

import br.com.alura.LiterAlura.model.*;
import br.com.alura.LiterAlura.repository.LivroRepository;
import br.com.alura.LiterAlura.service.ConsumoApi;
import br.com.alura.LiterAlura.service.ConverteDados;
import br.com.alura.LiterAlura.model.DadosLivro;
import br.com.alura.LiterAlura.model.Livro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/";
    private List<DadosLivro> dadosLivros = new ArrayList<>();

    private LivroRepository repositorio;
    private List<Livro> series = new ArrayList<>();
    private Optional<Livro> livroBusca;

    public Principal(LivroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    Escolha o número de sua opção: 
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - listar livros em um determinado idioma           
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarlivropelotitulo();
                    break;
                case 2:
                    listarlivrosregistrados();
                    break;
                case 3:
                    listarautoresregistrados();
                    break;
                case 4:
                    Listarautoresvivosemumdeterminadoano();
                    break;
                case 5:
                    listarlivrosemumdeterminadoidioma();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarlivropelotitulo() {
        DadosLivro dados = getDadosLivro();
        Livro livro = new Livro(dados);
        repositorio.save(livro);
        System.out.println(dados);
    }

    private DadosLivro getDadosLivro() {
        System.out.println("Digite o nome do livro para busca");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
        DadosLivro dados = conversor.obterDados(json, DadosLivro.class);
        return dados;
    }

    private void listarlivrosregistrados() {
        series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Livro::getIdioma))
                .forEach(System.out::println);
    }

    private void listarautoresregistrados() {
        series = repositorio.findAll();
        series.stream()
                .sorted(Comparator.comparing(Autor::getIdioma))
                .forEach(System.out::println);
    }

    private void Listarautoresvivosemumdeterminadoano() {
        listarautoresregistrados();
        if (livroBusca.isPresent()) {
            Livro livro = livroBusca.get();
            System.out.println("Digite o ano para autores vivo, neste ano: ");
            var anoDigitado = leitura.nextInt();
            leitura.nextLine();

            List<Autor> autorAno = repositorio.episodiosPorSerieEAno(livro, anoDigitado);
            autorAno.forEach(System.out::println);
        }
    }

    private void listarlivrosemumdeterminadoidioma() {
        System.out.println("Deseja buscar séries de que idioma? ");
        var nomeIdioma = leitura.nextLine();
        Idiomas idiomas = Idiomas.fromPortugues(nomeIdioma);
        List<Livro> livroPorIdiomas = repositorio.findByGenero(categoria);
        System.out.println("Livros do Idioma " + nomeGenero);
        livrosPorIdioma.forEach(System.out::println);
    }

}