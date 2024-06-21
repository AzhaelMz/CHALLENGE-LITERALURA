//package com.aluracursos.literalura.model;
//
//public enum AvailableLanguages {
//
//    SPANISH("es", "español"),
//    ENGLISH("en", "inglés"),
//    FRENCH("fr", "francés"),
//    ITALIAN("it", "italiano"),
//    PORTUGUES("pt", "portugúes");
//
//    private String languageCode;
//    private String languageName;
//
//    AvailableLanguages(String languageCode, String languageName){
//        this.languageCode = languageCode;
//        this.languageName = languageName;
//    }
//
//    public static AvailableLanguages getNameByCode(String code){
//        for (AvailableLanguages languages: AvailableLanguages.values()){
//            if (languages.languageCode.equalsIgnoreCase(code)){
//                return languages;
//            }
//        }
//        throw new IllegalArgumentException("Language not found" + code);
//    }
//    public static String getSpanishName(String enumName){
//        try{
//            AvailableLanguages languages = AvailableLanguages.valueOf(enumName.toUpperCase());
//            return languages.languageName;
//        }catch (IllegalArgumentException e){
//            throw new IllegalArgumentException("Language not found:" + enumName);
//        }
//    } ;
//}
