package com.aluracursos.literalura.model;

public enum AvailableLanguages {

    SPANISH("es", "español"),
    ENGLISH("en", "inglés"),
    FRENCH("fr", "francés"),
    ITALIAN("it", "italiano"),
    PORTUGUES("pt", "portugúes");

    private String languageCode;
    private String languageName;

    AvailableLanguages(String languageCode, String languageName){
        this.languageCode = languageCode;
        this.languageName = languageName;
    }
}
