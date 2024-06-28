package com.aluracursos.literalura.main;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.service.APIRequests;
import com.aluracursos.literalura.service.DataConverter;
import com.aluracursos.literalura.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private Scanner input = new Scanner(System.in);

    private static final String URL_BASE = "https://gutendex.com/books/";

    private APIRequests apiRequests = new APIRequests();

    private DataConverter converter = new DataConverter();

    private List<BookData> bookData = new ArrayList<>();

    private List<AuthorData> authorData = new ArrayList<>();

    private List<Book> bookSearched;


    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;

    }


    public BookData showingMenu() {
        System.out.println("Welcome to literalura, please choose an option to start");

        var option = -1;
        while (option != 0) {
            var menu = """
                    1.Search book by title.
                    2.List books founded
                    3. List authors""";
            System.out.println(menu);
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        return null;
    }


    private ResultsData getBooksDataFromApi() {
        ResultsData data = null;
        try {
            System.out.println("Please enter the name of the book to search on the web");
            var userTitle = input.nextLine();
            var json = APIRequests.getData(URL_BASE + "/?search=" + userTitle.replace(" ", "%20"));
            System.out.println(json);
            ResultsData resultsData = converter.getData(json, ResultsData.class);
            return resultsData;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void searchBookByTitle() {
        System.out.println("Write the name of the book you want to search");
        var userBook = input.nextLine();
        List<Book> bookSearched = bookRepository.searchByTitleContainsIgnoreCase(userBook);

        if(bookSearched.isEmpty()){
            System.out.println("Book not found in the data base");
            ResultsData resultsData = getBooksDataFromApi();
            Optional<BookData> bookDataOptional = resultsData.book().stream()
                    .filter(b -> b.title().toUpperCase().contains(userBook.toUpperCase()))
                    .findFirst();
            if (bookDataOptional.isPresent()) {
                BookData bookData = bookDataOptional.get();
                List<AuthorData> authorsData = bookData.authors();
                if (!authorsData.isEmpty()) {
                    AuthorData authorData = authorsData.get(0); // Tomar el primer autor de la lista
                    Author author = authorRepository.searchByName(authorData.name());
                    if (author == null) {
                        author = new Author(authorData);
                        authorRepository.save(author);
                    }
                    Book book = new Book(bookData, author);
                    bookRepository.save(book);
                    System.out.println("Book found and added to the database.");
                    System.out.println("The book is" + book);
                } else {
                    System.out.println("No authors found for the book in the API.");
                }
            } else {
                System.out.println("No books found in the API.");
            }
        } else {
            System.out.println("Books found in the local database:");
            bookSearched.forEach(book -> System.out.println(book.getTitle()));
        }
    }
}