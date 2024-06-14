package com.aluracursos.literalura;

import com.aluracursos.literalura.ppal.CPpal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication  implements CommandLineRunner {

	@Autowired
	private CPpal cPpal;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cPpal.muestraMenu();

//
	}
}
