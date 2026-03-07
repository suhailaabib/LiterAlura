package br.com.alura.LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer anodenascimento;
    private Integer anodefalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(){}

    public Autor(DadosAutor dadosAutor) {
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoDeNascimento(Integer anodenascimento) {
        this.anodenascimento = anodenascimento;
    }

    public void setAnoDeFalecimento(Integer anodefalecimento) {
        this.anodefalecimento = anodefalecimento;
    }

    public String getNome() { return nome; }
    public Integer getAnodenascimento() { return anodenascimento; }
    public Integer getAnodefalecimento() { return anodefalecimento; }
    public List <Livro> getLivros() { return livros; }


    public void setLivros (List<Livro> livros){
        livros.forEach(l -> l.setAutor(this));
        this.livros = livros;
    }


    @Override
    public String toString() {
        return
                "Autor: " + nome + "\n" +
                "Ano de Nascimento: " + anodenascimento + "\n" +
                "Ano de Falecimento: " + anodefalecimento + "\n" +
                        "Livros: " + livros.stream()
                        .map(l -> l.getTitulo())
                        .collect(Collectors.toList()) + "\n";
    }
}