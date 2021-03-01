package cl.example.imdb.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.example.imdb.entity.Movies;
import cl.example.imdb.service.IMoviesServices;
import io.swagger.annotations.ApiOperation;

/**
 * Clase controlador de la entidad peliculas.
 * 
 * @author julio
 * @version 1.0
 * @since 15/01/2021
 */
@RestController //
@RequestMapping("/imdb")
@CrossOrigin(origins="*")
public class MoviesController {

	/**
	 * Inyecta las dependencias del servicio de pelicula
	 */
	@Autowired
	private IMoviesServices moviesServices;

	/**
	 * Metodo que llama a la funcion save del servicio y guarda una pelicula
	 * 
	 * @param pelicula
	 * @return la pelicula guardada en la base de datos
	 */
	@PostMapping(value = "/add", produces = "application/json")
	@ApiOperation(value = "Agrega una pelicula", notes = "Agrega una nueva pelicula con los parametros ingresados")
	public ResponseEntity<?> agregar(@RequestBody Movies movie) {
		Movies movieNew = null;
		Map<String, Object>response = new HashMap<>();
		try {
			movieNew = moviesServices.save(movie);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getLocalizedMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Movies>(movieNew, HttpStatus.CREATED);

	}

	/**
	 * Metodo que llama a la funcion getMovies del servicio para obtener un
	 * listado de todas las peliculas
	 * 
	 * @return Lista de peliculas en la base de datos, si la BD esta vacia retorna un mensaje y un http status not found.
	 */
	@GetMapping(value = "/movies", produces = "application/json")
	@ApiOperation(value = "Busca todas las peliculas", notes = "Entrega una lista con todas las peliculas en la BD.")
	public ResponseEntity<?> getMovies() {
		List<Movies>foundMovies = moviesServices.getMovies();
		Map<String, Object> response = new HashMap<>();
		
		if (foundMovies.size() == 0) {
			response.put("mensaje", "No existen peliculas en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Movies>>(foundMovies, HttpStatus.OK);
	}

	/**
	 * Metodo que llama al metodo deleteMovieById y elimina a la pelicula con el
	 * id ingresado por el cliente.
	 * 
	 * @param id
	 * return si la pelicula con id ingresado existia en la BD, retorna un http status ok, si no se encuentra retorna un mensaje y un http status error.
	 */
	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	@ApiOperation(value = "Elimina pelicula", notes = "Elimina pelicula que coincida con el ID ingresado.")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
		moviesServices.deleteMovieById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar la pelicula de la base de datos");
			response.put("error", e.getLocalizedMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	/**
	 * Metodo que llama a la funcion updateMovies del servicio y actualiza los
	 * datos especificados por el usuario.
	 * 
	 * @param id
	 * @param objeto movie.
	 * @return el objeto pelicula agregado y  Http Status ok. Si no lo encuentra retorna un mensaje y un http status not found.
	 */
	@PutMapping(value = "/update/{id}", produces = "application/json")
	@ApiOperation(value = "Actualiza pelicula", notes = "Modifica los valores de la Pelicula por su Id, solo ingresar los parametros a modificar")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Movies movie) {
		
		Movies addedMovie = moviesServices.updateMovie(id, movie);
		Map<String, Object> response = new HashMap<>();
		if(addedMovie == null) {
			response.put("mensaje", "Error: no se pudo editar, la pelicula ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}else {
		return new ResponseEntity<Movies>(addedMovie,HttpStatus.OK);
		}
	}

	/**
	 * Método que llama a la función .getPeliculaByTitle() del servicio, la cual
	 * retorna la pelicula que estén en la DB con el Título ingresado.
	 * 
	 * @param nombrePelicula
	 * @return Pelicula que coincide con title ingresado. Si no lo encuentra retorna un mensaje y un http status de error.
	 */
	@GetMapping(value = "/bytitle", produces = "application/json")
	@ApiOperation(value = "Busca Pelicula por su Titulo", notes = "Entrega el documento de Pelicula que coincida con el Título ingresado.")
	public ResponseEntity<?> getMovieByTitle(@RequestParam String movieTitle) {
		
		List<Movies> foundMovies = moviesServices.getMovieByTitle(movieTitle);
		Map<String, Object> response = new HashMap<>();
		
		if (foundMovies.size() == 0) {
			response.put("mensaje", "La pelicula: ".concat(movieTitle.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Movies>>(foundMovies, HttpStatus.OK);
	}
	
	
	@GetMapping(value="find/{id}", produces = "application/json")
	public ResponseEntity<?> getPeliculaById(@PathVariable("id") Integer id){
		Movies pelicula = moviesServices.getMovieById(id);
		Map<String, Object> response = new HashMap<>();
		if(pelicula == null) {
			response.put("mensaje", "La pelicula ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Movies>(pelicula, HttpStatus.OK);
	}
}