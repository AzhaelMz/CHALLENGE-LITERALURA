package com.aluracursos.literalura.main;

//import com.aluracursos.literalura.repository.AuthorsRepository;
//import com.aluracursos.literalura.repository.BooksRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

//	@Autowired
//	private BooksRepository booksRepository;
//
//	private AuthorsRepository authorsRepository;


	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main();
		main.showingMenu();
	}
}
