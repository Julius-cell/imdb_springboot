package cl.example.imdb.service;

import java.util.List;
import java.util.Optional;

import cl.example.imdb.entity.Movies;


/**
 * Interfaz que define los métodos usados en el servicio que lo implemente.
 * 
 * @author julio
 * @version 1.0
 * @since 15/01/2021
 */
public interface IMoviesServices {

Movies getMovieById (Integer id);
	
	Movies save (Movies movie);
	
	List<Movies> getMovies();

	void deleteMovieById(Integer id);

	Movies updateMovie(Integer id, Movies movie);

	Movies validateUpdateMovie(Movies existingMovie, Movies newMovie);

	List<Movies> getMovieByTitle(String movieTitle);
}