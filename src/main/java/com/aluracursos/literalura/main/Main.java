package com.aluracursos.literalura.main;

import com.aluracursos.literalura.DataRequests.APIRequests;
import com.aluracursos.literalura.model.BooksData;
import com.aluracursos.literalura.repository.BooksRepository;
import com.aluracursos.literalura.service.DataConverter;

import java.util.Scanner;

public class Main {

    private Scanner input = new Scanner(System.in);

    private APIRequests apiRequests = new APIRequests();

    private final String URL_BASE = "https://gutendex.com/books/";

    private DataConverter converter = new DataConverter();

    private BooksRepository booksRepository;

    public Main(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }


    public void showingMenu(){
        System.out.println("Welcome to literalura, please choose an option to start");
        
        var option = -1;
        while (option != 0){
            var menu = """
                    1.Search book by title.""";
            System.out.println(menu);
            option = input.nextInt();
            input.nextLine();
            switch (option){
                case 1:
                    searchBookByTitle();
                    break;
            }
        }
    }

    private BooksData searchBookByTitle() {
        System.out.println("Please write the name of the book you want search");
        var userSearch = input.nextLine();
        var json = APIRequests.getData(URL_BASE + "?/search=" + userSearch.replace(" ", "%20"));
        System.out.println(json);
        BooksData data = converter.getData(json, BooksData.class);
        return data;
    }
}
