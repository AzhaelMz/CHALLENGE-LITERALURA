package com.aluracursos.literalura.model;

public class Books {

    private String title;

    private Authors authors;

    private String bookCategory;

    private AvailableLanguages languages;

    private int downloadCount;

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

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
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
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", bookCategory='" + bookCategory + '\'' +
                ", languages=" + languages +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
