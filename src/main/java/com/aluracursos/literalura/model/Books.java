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

    @Enumerated (EnumType.STRING)
    private AvailableLanguages languages;

    private int downloadCount;

    public Books(){}

    public Books(BooksData books, Authors authors){
        this.title = books.title();

        this.languages = books.languages().stream()
                .map(AvailableLanguages::getNameByCode)
                .collect(Collectors.toList())
                .get(0);
        this.downloadCount = books.downloadCount();
        this.authors = authors;
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

    public AvailableLanguages getLanguages() {
        return languages;
    }

    public void setLanguages(AvailableLanguages languages) {
        this.languages = languages;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + Id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", languages=" + languages +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
