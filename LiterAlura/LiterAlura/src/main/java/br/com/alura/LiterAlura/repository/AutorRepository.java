package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.anodenascimento<= :ano AND (a.anodefalecimento >= : ano OR a.anodefalecimento IS NULL)")
    List<Autor> obterAutoresVivosEmDeterminadoAno(int ano);
}
