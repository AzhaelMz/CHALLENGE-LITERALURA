package com.aluracursos.literalura.main;

import com.aluracursos.literalura.model.AuthorData;
import com.aluracursos.literalura.model.ResultsData;
import com.aluracursos.literalura.service.APIRequests;
//import com.aluracursos.literalura.model.Books;
import com.aluracursos.literalura.model.BookData;
//import com.aluracursos.literalura.repository.BooksRepository;
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


 //   private BooksRepository booksRepository;

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
                    listBooksFounded();
                    break;
                case 3:
                    listAuthorsOfTheBooksFound();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        return null;
    }

    private BookData getBooksDataFromApi() {
        System.out.println("Please enter the name of the book you want to search");
        var userInput = input.nextLine();
        var json = APIRequests.getData(URL_BASE + "/?search=" + userInput.replace(" ", "%20"));
        var userSearch = converter.getData(json, ResultsData.class);
        System.out.println(json);
        Optional<BookData> bookSearched = userSearch.books().stream()
                .filter(b -> b.title().toUpperCase().contains(userInput.toUpperCase()))
                .findFirst();
        if (bookSearched.isPresent()) {
            System.out.println("Book found");
            System.out.println(bookSearched.get());
            return bookSearched.get();
        } else {
            System.out.println("Sorry book not found");
            return null;
        }
    }

//    private AuthorsData getAuthorsDataFromApi(){
//        System.out.println("Please enter the name of the author you want to search");
//
//    }

    //1st option
    private void searchBookByTitle(){
        BookData data = getBooksDataFromApi();
        bookData.add(data);
    }
    //2nd option
    private void listBooksFounded(){
        if(bookData.isEmpty()){
            System.out.println("List empty");
        }else{
            System.out.println("List of books");
            bookData.forEach(System.out::println);
        }
    }
    //3rd option
    private void listAuthorsOfTheBooksFound(){
        if (bookData.isEmpty()){
            System.out.println("¡Ops!, not authors found. Please search a book first ;)");
        }else{
            System.out.println("Authors founded:");
            bookData.forEach(b -> b.authors().forEach(System.out::println));
        }
    }


}

