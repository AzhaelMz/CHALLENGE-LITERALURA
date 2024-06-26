package com.aluracursos.literalura.model;

import org.hibernate.annotations.processing.Find;

public enum FindByLanguages {
    SPANISH ("es", "Spanish"),

    ENGLISH ("en", "English"),

    FRENCH ("fr", "French");

    private String codeOfLanguage;

    private String nameOfLanguage;

    FindByLanguages(String codeOfLanguage, String nameOfLanguage) {
        this.codeOfLanguage = codeOfLanguage;
        this.nameOfLanguage = nameOfLanguage;
    }
    public static FindByLanguages getNameByCode(String code) {
        for (FindByLanguages language: FindByLanguages.values()){
            if (language.codeOfLanguage.equalsIgnoreCase(code)){
                return language;
            }
        }
        throw new IllegalArgumentException("Books not founded for this languages: "+ code);
    }
    public static String getAvailableLanguages() {
        StringBuilder languages = new StringBuilder();
        for (FindByLanguages language : FindByLanguages.values()){
            languages.append(language.codeOfLanguage).append("-").append(language.nameOfLanguage).append("\n");
        }
        return languages.toString();
    }
}
