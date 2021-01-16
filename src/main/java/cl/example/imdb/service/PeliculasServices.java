package cl.example.imdb.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.example.imdb.entity.Peliculas;
import cl.example.imdb.repository.PeliculasRepository;


/**
 * Clase principal  del servicio de peliculas.
 * 
 * @author julio
 * @version 1.0
 * @since 15/01/2021
 */
@Service
public class PeliculasServices implements IPeliculasServices {

	/*
	 * Método que inyecta las dependencias de peliculasRepository.
	 */
	@Autowired
	private PeliculasRepository peliculasRepository;

	/**
	 * Método que guarda una pelicula en la DB.
	 * @param pelicula Tipo Pelicula
	 * @return instancia de Peliculas
	 */
	@Override
	@Transactional
	public Peliculas save(Peliculas peliculas) {
		return peliculasRepository.save(peliculas);
	}

	/**
	 * Método que devuelve listado de peliculas.
	 */
	@Override
	@Transactional
	public List<Peliculas> getPeliculas() {
		return peliculasRepository.findAll();
	}
	
	/**
	 * Método que devuelve pelicula que coincida con el  Título ingresado.
	 * @return instancia de Peliculas solicitada
	 */
	@Override
	public List<Peliculas> getPeliculaByTitle(String nombrePelicula) {
		return peliculasRepository.findByTitleIgnoreCase(nombrePelicula);
	}

	/**
	 * Método para validar los datos de pelicula a actualizar.
	 * @return instancia de Peliculas actualizada
	 */
	@Override
	public Peliculas validarUpdatePelicula(Peliculas existingPelicula, Peliculas newPelicula) {	// cambiar a private
		String peliTitle = newPelicula.getTitle();
		if (peliTitle != null) {
			existingPelicula.setTitle(peliTitle);
		}
		String peliDirector = newPelicula.getDirector();
		if (peliDirector != null) {
			existingPelicula.setDirector(peliDirector);
		}
		String peliCountry = newPelicula.getCountry();
		if (peliCountry != null) {
			existingPelicula.setCountry(peliCountry);
		}
		String peliGenre = newPelicula.getGenre();
		if (peliGenre != null) {
			existingPelicula.setGenre(peliGenre);
		}
		Integer peliYear = newPelicula.getYear();
		if (peliYear != null) {
			existingPelicula.setYear(peliYear);
		}
		Float peliRating = newPelicula.getRating();
		if (peliYear != null) {
			existingPelicula.setRating(peliRating);
		}
		return existingPelicula;
	}
	
	/**
	 * Método que actualiza una pelicula en la DB.
	 */
	@Override
	@Transactional
	public void updatePelicula(Integer id, Peliculas pelicula) {
		Optional<Peliculas> peliculaData = peliculasRepository.findById(id);
		
		if (peliculaData.isPresent()) {
			Peliculas nuevaPelicula = validarUpdatePelicula(peliculaData.get(), pelicula);
			peliculasRepository.save(nuevaPelicula);
		} else {
			peliculaNotFound(id);
		}
	}

	/**
	 * Método que elimina una pelicula de la DB.
	 */
	@Transactional
	@Override
	public void deletePeliculaById(Integer id) {
		Optional<Peliculas> peliculasOptional = peliculasRepository.findById(id);
		if (!peliculasOptional.isPresent()) {
			peliculaNotFound(id);
		} else {
			peliculasRepository.deleteById(id);
		}
	}

	
	/**
	 *  Método que valida que exista una pelicula en la DB por su ID.
	 */
	@Override
	public void peliculaNotFound(Integer id) {
		throw new RuntimeException("No se encontró pelicula con el id: " + id + ".");
	}
}
