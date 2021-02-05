package cl.example.imdb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.example.imdb.entity.Movies;


/**
 * Interfaz que extiende de JpaRepository para obtener métodos CRUD genéricos con la entidad en la DB.
 * También permite escanear la ruta de clases para esta interfaz y crear un bean Spring para ella.
 * 
 * @author julio
 * @version 1.0
 * @since 15/01/2021
 */
public interface MoviesRepository extends JpaRepository<Movies, Integer> {

	/**
	 * 
	 * @param nombrePelicula
	 * @return Las peliculas de la BD que coinciden con la busqueda.
	 */
	List<Movies>findByTitleContainingIgnoreCase(String movieTitle);
	
}
