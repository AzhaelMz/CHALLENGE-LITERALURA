package com.aluracursos.literalura;

import com.aluracursos.literalura.model.Author;
import com.aluracursos.literalura.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void listAuthors() {
        List<Author> authors = authorRepository.findAll();

        authors.stream()
                .forEach(System.out::println);
    }

    public List<Author> listAuthorsByYear (int year){
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .filter(a -> a.getBirthYear()!= null && a.getBirthYear() <= year &&
                        (a.getDeathYear() == null || a.getDeathYear() >= year))
                .collect(Collectors.toList());
    }
}
