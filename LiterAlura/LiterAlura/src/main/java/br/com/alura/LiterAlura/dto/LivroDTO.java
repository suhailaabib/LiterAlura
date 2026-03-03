package br.com.alura.LiterAlura.dto;

import br.com.alura.LiterAlura.model.Idiomas;

public record LivroDTO(String título, String autor, Idiomas idioma, double numerodownload ) {
}
