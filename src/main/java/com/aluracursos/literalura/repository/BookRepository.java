package com.aluracursos.literalura.repository;

//import com.aluracursos.literalura.model.AvailableLanguages;
import com.aluracursos.literalura.model.Book;
import com.aluracursos.literalura.model.FindByLanguages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> searchByTitleContainsIgnoreCase(String bookName);

    List<Book> searchByLanguages (FindByLanguages languages);

    Book searchByTitle(String title);
}
