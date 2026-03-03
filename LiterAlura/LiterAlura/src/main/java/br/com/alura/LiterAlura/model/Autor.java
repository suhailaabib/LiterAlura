package br.com.alura.LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anodenascimento;
    private Integer anodefalecimento;

    @OneToMany
    private Livro livro;

    public Autor(){}

    public Autor(Integer numeroTemporada, DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.anodenascimento = dadosAutor.anodenascimento();
        this.anodefalecimento = dadosAutor.anodefalecimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoDeNascimento() {
        return anodenascimento;
    }

    public void setAnoDeNascimento(Integer anodenascimento) {
        this.anodenascimento = anodenascimento;
    }

    public Integer getAnoDeFalecimento() {
        return anodefalecimento;
    }

    public void setAnoDeFalecimento(Integer anodefalecimento) {
        this.anodefalecimento = anodefalecimento;
    }

    @Override
    public String toString() {
        return

                "nome='" + nome + '\'' +
                ", anodenascimento=" + anodenascimento +
                ", anodefalecimento=" + anodefalecimento;
    }
}