package com.alura.literatura;

import com.alura.literatura.principal.Principal;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplicationConsola implements CommandLineRunner {
	@Autowired //inyeccion de dependencia
	private LibroRepository repository; //cual va ser la dependencia


	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplicationConsola.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.muestraElMenu();
	}
}
