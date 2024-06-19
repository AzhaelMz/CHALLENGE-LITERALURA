package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.AvailableLanguages;
import com.aluracursos.literalura.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Long> {

    List<Books> searchByTitleIgnoreCase(String bookName);

    List<Books> searchByLanguage (AvailableLanguages languages);

    Books searchByTitle(String title);
}
