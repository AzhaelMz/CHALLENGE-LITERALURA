package com.aluracursos.literalura.model;

import java.util.List;

//@Entity
//@Table(name = "books")
public class Books {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long Id;

//    @Column(unique = true)
    private String title;

//    @ManyToOne
    private Authors authors;

    //    @Enumerated (EnumType.STRING)
    private List<String> languages;

    private Double downloadCount;

//    public Books(){}

//    public Books(BooksData booksData) {
//        this.title = booksData.title();
//        this.authors = (Authors) booksData.authors();
//        this.languages = booksData.languages();
//        this.downloadCount = booksData.downloadCount();
//
//    }

//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long id) {
//        Id = id;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Authors getAuthors() {
        return authors;
    }

    public void setAuthors(Authors authors) {
        this.authors = authors;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

//    public AvailableLanguages getLanguages() {
//        return languages;
//    }
//
//    public void setLanguages(AvailableLanguages languages) {
//        this.languages = languages;
//    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = (double) downloadCount;
    }

    @Override
    public String toString() {
        return "Books{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", languages=" + languages +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
