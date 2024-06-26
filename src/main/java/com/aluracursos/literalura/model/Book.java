package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.stream.Collectors;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "authors_name")
    private Author author;

    @Enumerated(EnumType.STRING)
    private FindByLanguages languages;

    private Double downloadCount;

    public Book(BookData bookData, Author author){ //se traen los datos del record BooksData
        this.title = bookData.title();
        this.author = author;
        this.languages = bookData.languages().stream()
                .map(FindByLanguages::getNameByCode)
                .collect(Collectors.toList())
                .get(0);
        this.downloadCount = bookData.downloadCount();

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
