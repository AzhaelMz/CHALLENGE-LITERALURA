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
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "author_name")
    private String authorName;

    @Enumerated(EnumType.STRING)
    private FindByLanguages languages;

    private Double downloadCount;

    public Book() {
    }

    public Book(BookData book, Author author) { //se traen los datos del record BooksData
        this.title = book.title();
        this.languages = book.languages().stream()
                .map(FindByLanguages::getNameByCode)
                .collect(Collectors.toList())
                .get(0);
        this.downloadCount = book.downloadCount();
        this.author = author;

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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Title ='" + title + '\'' +
                ", Author =" + (author != null ? author.getName() + " (born " + author.getBirthYear() + ", died " + author.getDeathYear() + ")" : "No author") +
                ", Languages =" + languages +
                ", DownloadCount =" + downloadCount ;
    }
}
