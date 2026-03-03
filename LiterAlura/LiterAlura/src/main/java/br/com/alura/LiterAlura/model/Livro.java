package br.com.alura.LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    @Enumerated(EnumType.STRING)
    private double numerodownload;

    @ManyToOne(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor = new ArrayList<>();

    public Livro() {}

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.autor = dadosLivro.autor();
        this.idioma = Idiomas.fromString(dadosLivro.idioma().split(",")[0].trim());
        this.numerodownload = dadosLivro.numerodownload();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Idiomas getIdioma() {
        return idioma;
    }

    public void setIdioma(Idiomas idioma) {
        this.idioma = idioma;
    }

    public double getnumerodownload() {
        return numerodownload;
    }

    public void setnumerodownload(double numerodownload) {
        this.numerodownload = numerodownload;
    }

    @Override
    public String toString() {
        return
                        ", titulo='" + titulo + '\'' +
                        ", autor='" + autor + '\'' +
                        ", idioma='" + idioma + '\' '+
                        ", numerodownload=" + numerodownload + '\'' ;

    }
}