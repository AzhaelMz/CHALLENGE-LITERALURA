package com.aluracursos.literalura.main;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.service.APIRequests;
import com.aluracursos.literalura.service.DataConverter;

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

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;

    }

//    public Main(BooksRepository booksRepository) {
//        this.booksRepository = booksRepository;
//    }


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
//                    listBooksFounded();
//                    break;
//                case 3:
//                    listAuthorsOfTheBooksFound();
//                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        return null;
    }


    private BookData getBooksDataFromApi() {

        System.out.println("Please enter the name of the book you want to search");
        var userTitle = input.nextLine();
        var json = APIRequests.getData(URL_BASE + "/?search=" + userTitle.replace(" ", "%20"));
        System.out.println(json);
        var data = converter.getData(json, ResultsData.class);
        Optional<BookData> bookSearched = data.books().stream()
                .filter(b -> b.title().toUpperCase().contains(userTitle.toUpperCase()))
                .findFirst();
            return bookData;
        }
    }
    //Option 1
    private void searchBookByTitle(){
        BookData bookData = getBooksDataFromApi();
        if(bookData!= null){
            System.out.println(bookData);
        }
    }
}

//

//
//           // Crea y guarda el libro
//           Book book = new Book(bookData, author);
//           bookRepository.save(book);
//       } else {
//           System.out.println("No authors found for this book");
//       }
//
//            return bookData;
//        } else {
//            System.out.println("Sorry book not found");
//            return null;
//        }
//    }

//    private void searchBookByTitle() {
//        BookData bookData = getBooksDataFromApi();
//        if (bookData != null) {
//            // Obtén el autor del libro
//            if (!bookData.author().isEmpty()) {
//                AuthorData authorData = bookData.author().get(0); // Asumimos que el libro tiene al menos un autor
//                Author author = new Author(authorData);
//                // Guarda el autor en la base de datos si no existe
//                    authorRepository.save(author);
//                }
//                // Crea y guarda el libro
//                Book book = new Book(bookData);
//                bookRepository.save(book);
//            } else {
//                System.out.println("No authors found for this book");
//            }
//        }
//    }
//
//    private void listBooksFounded() {
//        List<Book> books = bookRepository.findAll();
//        if (books.isEmpty()) {
//            System.out.println("No books found.");
//        } else {
//            for (Book book : books) {
//                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor().getName());
//            }
//        }
//    }
//
//    private void listAuthorsOfTheBooksFound() {
//        List<Author> authors = authorRepository.findAll();
//        if (authors.isEmpty()) {
//            System.out.println("No authors found.");
//        } else {
//            for (Author author : authors) {
//                System.out.println("Author: " + author.getName());
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        // Aquí debes inicializar tus repositorios, tal vez usando Spring Boot o una instancia manual
//        BookRepository bookRepository = // Inicializa tu repositorio aquí
//                AuthorRepository authorRepository = // Inicializa tu repositorio aquí
//
//                Main main = new Main(bookRepository, authorRepository);
//        main.showingMenu();
//}

