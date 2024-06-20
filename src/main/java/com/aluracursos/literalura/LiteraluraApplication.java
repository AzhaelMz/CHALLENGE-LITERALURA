package com.aluracursos.literalura;

import com.aluracursos.literalura.main.Main;
import com.aluracursos.literalura.model.Authors;
import com.aluracursos.literalura.repository.AuthorsRepository;
import com.aluracursos.literalura.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BooksRepository booksRepository;

	private AuthorsRepository authorsRepository;


	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(booksRepository);
		main.showingMenu();
	}
}
