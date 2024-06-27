package com.aluracursos.literalura.main;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.service.APIRequests;
import com.aluracursos.literalura.service.DataConverter;
import com.aluracursos.literalura.model.Book
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
    
    private Optional<Book> bookSearched;


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
//                    listBookFound();
                  break;
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
                .filter(l -> l.title().toUpperCase().contains(userTitle.toUpperCase()))
                .findFirst();
        if (bookSearched.isPresent()){
            System.out.println("Book found");
            System.out.println(bookSearched);
            bookRepository.save();
        }


    }
    //1st Option
    private void searchBookByTitle(){
        System.out.println("Please, write the name of the book");
        var bookName = input.nextLine();
        bookSearched = bookRepository.searchByTitleContainsIgnoreCase(bookName);
        
        if (bookSearched.isPresent()){
            System.out.println("The book searched is: " + bookSearched.get());
        }else{
            System.out.println("Book is not in the data base, lets see in the web");

            }

    }

    private void saveApiDataToDataBase (ResultsData apiData){
        ResultsData resultsData = getBooksDataFromApi();

        Optional<BookData> bookData = resultsData.books().stream()
                .filter(b -> b.title().toUpperCase().contains(toString()))
                .findFirst();
        if (bookData.isPresent(){
            System.out.println("Book found in the web");
            System.out.println(bookData.get());

        }
    }

}