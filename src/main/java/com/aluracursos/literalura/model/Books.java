package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.stream.Collectors;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String title;

    @ManyToOne
    private Authors authors;

    @Enumerated(EnumType.STRING)
    private FindByLanguages languages;

    private Double downloadCount;

    public Books(BooksData booksData, Authors authors){ //se traen los datos del record BooksData
        this.title = booksData.title();
        this.authors = authors;
        this.languages = booksData.languages().stream()
                .map(FindByLanguages::getNameByCode)
                .collect(Collectors.toList())
                .get(0);
        this.downloadCount = booksData.downloadCount();

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

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

    public FindByLanguages getLanguages() {
        return languages;
    }

    public void setLanguages(FindByLanguages languages) {
        this.languages = languages;
    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Double downloadCount) {
        this.downloadCount = downloadCount;
    }



}
