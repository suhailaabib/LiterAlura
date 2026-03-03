package br.com.alura.LiterAlura.model;

public enum Idiomas {

    es("es", "Espanhol"),
    fr("fr", "Francês"),
    en("en", "Inglês"),
    pt("pt", "Português");

    private String idiomaApi;
    private String idiomaPortugues;

    Idiomas(String idiomaApi, String idiomaPortugues){
        this.idiomaApi = idiomaApi;
        this.idiomaPortugues = idiomaPortugues;
    }

    public static Idiomas fromString(String text) {
        for (Idiomas idioma : Idiomas.values()) {
            if (idioma.idiomaApi.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma não encontrado para a escrita fornecida: " + text);
    }

    public static Idiomas fromPortugues(String text) {
        for (Idiomas idioma : Idiomas.values()) {
            if (idioma.idiomaPortugues.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma não encontrado para a string fornecida: " + text);
    }
}