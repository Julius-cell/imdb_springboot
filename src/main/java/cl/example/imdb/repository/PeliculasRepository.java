package cl.example.imdb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.example.imdb.entity.Peliculas;


/**
 * Interfaz que extiende de JpaRepository para obtener métodos CRUD genéricos con la entidad en la DB.
 * También permite escanear la ruta de clases para esta interfaz y crear un bean Spring para ella.
 * 
 * @author julio
 * @version 1.0
 * @since 15/01/2021
 */
public interface PeliculasRepository extends JpaRepository<Peliculas, Integer> {

	/**
	 * Retorna la entidad identificada por su ID
	 */
	Optional<Peliculas> findById(Integer id);

	/**
	 * Retorna la entidad identificada por tu Título
	 */
	List<Peliculas> findByTitleContainingIgnoreCase(String nombrePelicula);
	
}
