package br.com.alura.LiterAlura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    private String idioma;
    private Double numerodownload;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();

        if (dadosLivro.autores() != null && !dadosLivro.autores().isEmpty()) {
            DadosAutor dadosAutor = dadosLivro.autores().get(0);

            this.autor = new Autor(dadosAutor);

            this.autor.setLivros(java.util.Collections.singletonList(this));
        } else {
            this.autor = null;
        }

        this.idioma = (dadosLivro.idiomas() != null && !dadosLivro.idiomas().isEmpty())
                ?dadosLivro.idiomas().get(0)
                :"Desconhecido";

        this.numerodownload = dadosLivro.numerodownload();
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id){
        this.id = id;
    }

    public String getTitulo () {
        return titulo;
    }

    public void setTitulo (String titulo){
        this.titulo = titulo;
    }

    public Autor getAutor () {
        return autor;
    }

    public void setAutor (Autor autor){
        this.autor = autor;
    }

    public String getIdioma () {
        return idioma;
    }

    public void setIdioma (String idioma){
        this.idioma = idioma;
    }

    public Double getnumerodownload () {
        return numerodownload;
    }

    public void setnumerodownload (Double numerodownload){
        this.numerodownload = numerodownload;
    }

    @Override
    public String toString () {
        return
                "*****LIVRO*****\n" +
                        "Titulo: " + titulo + "\n" +
                        "Autor: " + (autor != null ? autor.getNome() : "Desconhecido") + "\n" +
                        "Idioma: " + idioma + "\n" +
                        "Número de Downloads: " + numerodownload + "\n" +
                        "***************";

    }
}
