package com.aluracursos.literalura.main;

import com.aluracursos.literalura.model.ResultsData;
import com.aluracursos.literalura.service.APIRequests;
//import com.aluracursos.literalura.model.Books;
import com.aluracursos.literalura.model.BooksData;
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

//    private List<BooksData> booksData = new ArrayList<>();

 //   private BooksRepository booksRepository;

//    public Main(BooksRepository booksRepository) {
//        this.booksRepository = booksRepository;
//    }


    public BooksData showingMenu() {
        System.out.println("Welcome to literalura, please choose an option to start");

        var option = -1;
        while (option != 0) {
            var menu = """
                    1.Search book by title.
                    2.List books founded""";
            System.out.println(menu);
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    getDataFromApi();
                    break;
//                case 2:
//                    listBooksFounded();
//                    break;
            }
        }
        return null;
    }

    private BooksData getDataFromApi(){
        System.out.println("Please enter the name od the book you want to search");
        var userSearch= input.nextLine();
        var json = APIRequests.getData(URL_BASE + "/?search="+userSearch.replace(" ","%20") );
        var userFound = converter.getData(json, ResultsData.class);
        Optional<BooksData> searchedBook = userFound.books().stream()
                .filter(l -> l.title().toUpperCase().contains(userSearch.toUpperCase())) //lambda busca el primero resultado .
                .findFirst();
        if (searchedBook.isPresent()){
            System.out.println("Book founded");
            System.out.println(searchedBook.get());
        }else{
            System.out.println("Sorry book not found");
        }
        return null;
    }

//    private void searchBookByTitle(){
//        BooksData data = getDataFromApi();
//        Books books = new Books(data);
//        System.out.println(data);
//    }


}

