package br.com.alura.LiterAlura.principal;

import br.com.alura.LiterAlura.model.*;
import br.com.alura.LiterAlura.repository.AutorRepository;
import br.com.alura.LiterAlura.repository.LivroRepository;
import br.com.alura.LiterAlura.service.ConsumoApi;
import br.com.alura.LiterAlura.service.ConverteDados;
import br.com.alura.LiterAlura.model.DadosLivro;
import br.com.alura.LiterAlura.model.Livro;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/";

    private LivroRepository repositoriol;

    private AutorRepository repositorioa;

    public Principal(LivroRepository repositoriol, AutorRepository repositorioa) {
        this.repositoriol = repositoriol;
        this.repositorioa = repositorioa;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                   
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - listar livros em um determinado idioma
                    0 - Sair
                    
                    Escolha o número de sua opção:
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
        System.out.println("Digite o nome do livro para busca: ");
        var nomeLivro = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + "?search=" + nomeLivro.replace(" ", "%20"));
        Resultado dadosR = conversor.obterDados(json, Resultado.class);

        if (dadosR.livros() != null && !dadosR.livros().isEmpty()) {
            DadosLivro dadosLivro = dadosR.livros().get(0);
            Livro livro = new Livro(dadosLivro);

            Optional<Autor> autorBanco = repositorioa.findByNome(livro.getAutor().getNome());

            if (autorBanco.isPresent()) {
                livro.setAutor(autorBanco.get());
            } else {
                Autor novoAutor = livro.getAutor();
                repositorioa.save(novoAutor);
            }

            Optional<Livro> livroExistente = repositoriol.findByTituloIgnoreCase(livro.getTitulo());

            if (livroExistente.isPresent()) {
                System.out.println("\n O livro já está cadastrado no banco!");
            } else {
                repositoriol.save(livro);
                System.out.println("\n Livro salvo com sucesso!");
            }

            System.out.println(livro);

        }
    }


    private void listarlivrosregistrados() {
        List<Livro> livros = repositoriol.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    private void listarautoresregistrados() {
        List<Autor> autores = repositorioa.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
    }

    private void Listarautoresvivosemumdeterminadoano() {
        System.out.println("Digite o ano que deseja buscar: ");

        var ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = repositorioa.obterAutoresVivosEmDeterminadoAno(ano);

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado vivo neste ano");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarlivrosemumdeterminadoidioma() {
        System.out.println("""
                Insira o idioma que gostaria de buscar:
                
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);

        var idioma = leitura.nextLine();

        List<Livro> livroPorIdiomas = repositoriol.findByIdiomaIgnoreCase(idioma);

        if (livroPorIdiomas.isEmpty()) {
            System.out.println("Não temos livros para esse idioma inserido no banco de dados");
        } else {
            livroPorIdiomas.forEach(System.out::println);
        }
    }
}