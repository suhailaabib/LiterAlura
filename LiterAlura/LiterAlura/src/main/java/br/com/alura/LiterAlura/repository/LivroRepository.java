package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Idiomas;
import br.com.alura.LiterAlura.model.Autor;
import br.com.alura.LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTituloContainingIgnoreCase(String nomeLivro);

    List<Livro> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, double avaliacao);

    List<Livro> findTop5ByOrderByAvaliacaoDesc();

    List<Livro> findByGenero(Idiomas idioma);

}