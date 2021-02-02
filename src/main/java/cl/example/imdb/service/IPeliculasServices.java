package cl.example.imdb.service;

import java.util.List;
import java.util.Optional;

import cl.example.imdb.entity.Peliculas;


/**
 * Interfaz que define los métodos usados en el servicio que lo implemente.
 * 
 * @author julio
 * @version 1.0
 * @since 15/01/2021
 */
public interface IPeliculasServices {

	Peliculas save(Peliculas alumno);

	void deletePeliculaById(Integer id);

	void peliculaNotFound(Integer id);

	List<Peliculas> getPeliculas();

	void updatePelicula(Integer id, Peliculas pelicula);

	Peliculas validarUpdatePelicula(Peliculas existingPelicula, Peliculas newPelicula);

	List<Peliculas> getPeliculaByTitle(String nombrePelicula);

	/**
	 * Método que devuelve pelicula por su ID.
	 */
	Optional<Peliculas> getPeliculaById(Integer id);
}