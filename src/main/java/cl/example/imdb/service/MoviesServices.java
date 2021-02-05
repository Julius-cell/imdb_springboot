package cl.example.imdb.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.example.imdb.entity.Movies;
import cl.example.imdb.repository.MoviesRepository;


/**
 * Clase principal  del servicio de peliculas.
 * 
 * @author julio
 * @version 1.0
 * @since 15/01/2021
 */
@Service
public class MoviesServices implements IMoviesServices {

	@Autowired
	private MoviesRepository moviesRepository;

	/**
	 * Metodo que guarda una nueva pelicula en la bd.
	 * @param objeto pelicula.
	 * @return la pelicula guardada en la base de datos.
	 */
	@Transactional
	@Override
	public Movies save(Movies movie) {
		return moviesRepository.save(movie);
	}

	/**
	 * Metodo para buscar todas las peliculas en la bd.
	 * 
	 * @return una lista de todas las peliculas.
	 */
	@Override
	public List<Movies> getMovies() {
		return moviesRepository.findAll();
	}

	/**
	 * Metodo que valida que cada parametro del objeto pelicula ingresado por el
	 * cliente, no sea nulo.
	 * 
	 * @param objeto pelicula existente
	 * @param objeto pelicula con la informacion editada Si algun parametro del
	 *               objeto con la informacion editada es distinto de nulo, se
	 *               reemplaza con el parametro ingresado por el cliente en el nuevo
	 *               objeto.
	 */
	@Override
	public Movies validateUpdateMovie(Movies existingMovie, Movies newMovie) {

		String peliTitle = newMovie.getTitle();
		if (peliTitle != null) {
			existingMovie.setTitle(peliTitle);
		}
		String peliDirector = newMovie.getDirector();
		if (peliDirector != null) {
			existingMovie.setDirector(peliDirector);
		}
		
		String peliGenre = newMovie.getGenre();
		if (peliGenre != null) {
			existingMovie.setGenre(peliGenre);
		}
		Integer peliYear = newMovie.getYear();
		if (peliYear != null) {
			existingMovie.setYear(peliYear);
		}
		Float peliRating = newMovie.getRating();
		if (peliYear != null) {
			existingMovie.setRating(peliRating);
		}
		String peliImage = newMovie.getImage();
		if (peliImage != null) {
			existingMovie.setImage(peliImage);
		}
		String peliDescription = newMovie.getDescription();
		if (peliDescription != null) {
			existingMovie.setDescription(peliDescription);
		}
		String peliDuration = newMovie.getDuration();
		if (peliDuration != null) {
			existingMovie.setDuration(peliDuration);
		}
		String peliLanguage = newMovie.getLanguage();
		if (peliLanguage != null) {
			existingMovie.setLanguage(peliLanguage);
		}
		

		return existingMovie;

	}

	/**
	 * Metodo que actualiza una pelicula en la base de datos.
	 * 
	 * @param id     de la pelicula que se quiere editar.
	 * @param objeto pelicula con la informacion que se quiere editar.
	 */
	@Override
	@Transactional
	public Movies updateMovie(Integer id, Movies movie) {
		Movies movieData = moviesRepository.findById(id).orElse(null);
		Movies newMovie = null;
		if (movieData != null) {
			newMovie = validateUpdateMovie(movieData, movie);
			return moviesRepository.save(newMovie);
			}else {
				return newMovie;
			}
		 
	}

	/**
	 * Metodo que elimina una pelicula en la base de datos, segun el id.
	 * 
	 * @param Integer id.
	 */
	@Transactional
	@Override
	public void deleteMovieById(Integer id) {
		moviesRepository.deleteById(id);
	}

	
	/**
	 * Método que devuelve pelicula que coincida con el  Título ingresado.
	 * @param String con el nombre de la pelicula.
	 * @return lista con peliculas con el titulo solicitado.
	 */
	@Override
	public List<Movies> getMovieByTitle(String movieTitle) {
		return moviesRepository.findByTitleContainingIgnoreCase(movieTitle);
	}

	@Override
	public Movies getMovieById(Integer id) {
		return moviesRepository.findById(id).orElse(null);
	}
}
