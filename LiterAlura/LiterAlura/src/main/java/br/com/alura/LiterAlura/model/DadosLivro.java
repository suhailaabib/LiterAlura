package br.com.alura.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String titulo,
                         @JsonAlias("authors") List<DadosAutor> autores,
                         @JsonAlias("languages") List <String> idiomas,
                         @JsonAlias("download_count") Double numerodownload)

   {
       @Override
       public String toString(){

           return "************LIVRO************" +
                   "\nTitulo: " + titulo +
                   "\nAutor: " + ((autores != null && !autores().isEmpty()) ? autores.get(0).nome() : "Desconhecido") +
                   "\nIdioma: " + ((idiomas != null && !idiomas().isEmpty()) ? idiomas.get(0) : "Desconhecido") +
                   "\nNúmero de downloads: " + numerodownload +
                   "\n*****************************";




       }

   }