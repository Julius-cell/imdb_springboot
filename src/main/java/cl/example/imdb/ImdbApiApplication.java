package cl.example.imdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * Clase inicial del microservicio.
 * 
 * @author julio
 * @version 1.0
 * @since 15/01/2021
 */
@SpringBootApplication		
public class ImdbApiApplication extends SpringBootServletInitializer {
	
	/**
	 * Método principal de la clase.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ImdbApiApplication.class, args);
	}
}
