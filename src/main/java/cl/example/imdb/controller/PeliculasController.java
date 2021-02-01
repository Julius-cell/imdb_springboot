package cl.example.imdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cl.example.imdb.entity.Peliculas;
import cl.example.imdb.service.IPeliculasServices;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

/**
 * Clase controlador de la entidad peliculas.
 * 
 * @author julio
 * @version 1.0
 * @since 15/01/2021
 */
@RestController //
@RequestMapping("/imdb")
@Log4j2
@CrossOrigin(origins="*")
public class PeliculasController {

	/**
	 * Inyecta la dependencia del servicio Peliculas.
	 */
	@Autowired
	private IPeliculasServices peliculasServices;		// Inversión de dependencias *

	/**
	 * Método que llama a la función .save() del servicio y crea una pelicula.
	 * 
	 * @param pelicula Tipo Pelicula
	 * @return Pelicula guardada en la DB
	 */
	@PostMapping(value = "/add", produces = "application/json")
	@ApiOperation(value = "Añade pelicula", notes = "Añade pelicula a la DB con los parámetros que se entreguen.")
	public ResponseEntity<Peliculas> agregar(@RequestBody Peliculas pelicula) {
		try {
			pelicula = peliculasServices.save(pelicula);
		} catch (Exception e) {
			log.info(e.getMessage());		// Logger de forma profesional (by loj4j)
		}
		return new ResponseEntity<Peliculas>(pelicula, HttpStatus.OK);
	}

	/**
	 * Método que llama a la función .getPeliculas() del servicio, la cual retorna
	 * un listado de todas las peliculas que estén en la DB.
	 * 
	 * @return Todas las Peliculas
	 */
	@GetMapping(value = "/peliculas", produces = "application/json")
	@ApiOperation(value = "Todas las peliculas", notes = "Entrega todas las peliculas.")
	public ResponseEntity<List<Peliculas>> getPeliculas() {
		return new ResponseEntity<List<Peliculas>>(peliculasServices.getPeliculas(), HttpStatus.OK);
	}
	
	/**
	 * Método que llama a la función .getPeliculaByTitle() del servicio, la cual retorna
	 * la pelicula que estén en la DB con el Título ingresado.
	 * 
	 * @param nombrePelicula
	 * @return Pelicula con title ingresado
	 */
	@GetMapping(value = "/bytitle", produces = "application/json")
	@ApiOperation(value = "Busca Pelicula por su Titulo",
				notes = "Entrega el documento de Pelicula que coincida con el Título ingresado.")
	public ResponseEntity<List<Peliculas>> buscarPeliculaxTitle( @RequestParam String nombrePelicula){
		return new ResponseEntity<List<Peliculas>>( peliculasServices.getPeliculaByTitle(nombrePelicula),HttpStatus.OK) ;
	}

	/**
	 * Método que llama a la función .updatePelicula() del servicio y actualiza los
	 * datos especificados por el usuario.
	 * 
	 * @param id
	 * @param pelicula Tipo Pelicula
	 * @return HTTP status 200 OK
	 */
	@PutMapping(value = "/update/{id}", produces = "application/json")
	@ApiOperation(value = "Actualiza pelicula", notes = "Modifica los valores de la Pelicula por su Id.")
	public ResponseEntity<Peliculas> update(@PathVariable("id") Integer id, @RequestBody Peliculas pelicula) {
		peliculasServices.updatePelicula(id, pelicula);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Método que llama a la función .deletePeliculaById() del servicio y elimina
	 * una pelicula según el id.
	 * 
	 * @param id
	 * @return HTTP status 204 NO_CONTENT
	 */
	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	@ApiOperation(value = "Elimina pelicula", notes = "Elimina pelicula que coincida con el ID ingresado.")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("id") Integer id) {
		peliculasServices.deletePeliculaById(id);
	}
}