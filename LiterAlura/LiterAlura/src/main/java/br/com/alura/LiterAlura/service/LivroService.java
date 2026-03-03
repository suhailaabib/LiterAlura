package br.com.alura.LiterAlura.service;

import br.com.alura.LiterAlura.dto.AutorDTO;
import br.com.alura.LiterAlura.dto.LivroDTO;
import br.com.alura.LiterAlura.model.Idiomas;
import br.com.alura.LiterAlura.model.Livro;
import br.com.alura.LiterAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LivroService {

    @Autowired
    private LivroRepository repositorio;

    public List<LivroDTO> obterTodasAsSeries() {
        return converteDados(repositorio.findAll());
    }

    private List<LivroDTO> converteDados(List<Livro> livros) {
        return livros.stream()
                .map(s -> new LivroDTO(l.getId(), l.getTitulo(), l.getAutor(), l.getIdioma(), l.getnumerodownload()))
                .collect(Collectors.toList());
    }

    public LivroDTO obterPorId(Long id) {
        Optional<Livro> livro = repositorio.findById(id);

        if (livro.isPresent()) {
            Livro l = livro.get();
            return new LivroDTO(l.getId(), l.getTitulo(), l.getAutor(), l.getIdioma(), l.getnumerodownload());
        }
        return null;
    }

    public List<AutorDTO> obterTodasTemporadas(Long id) {
        Optional<Livro> livro = repositorio.findById(id);

        if (livro.isPresent()) {
            livro l = livro.get();
            return l.getEpisodios().stream()
                    .map(e -> new AutorDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<AutorDTO> obterTemporadasPorNumero(Long id, Long numero) {
        return repositorio.obterEpisodiosPorTemporada(id, numero)
                .stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<LivroDTO> obterLivrosPeloIdioma(String nomelinguagem) {
        Idiomas categoria = Idiomas.fromPortugues(nomeIdioma);
        return converteDados(repositorio.findByGenero(categoria));
    }

    public List<AutorDTO> obterTopEpisodios(Long id) {
        var livro = repositorio.findById(id).get();
        return repositorio.topEpisodiosPorSerie(idioma)
                .stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                .collect(Collectors.toList());
    }
}