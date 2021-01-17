package cl.example.imdb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * Entidad del objeto Peliculas.
 * 
 * @author julio
 * @version 1.0
 * @since 15/01/2021
 */
@Data			// Genera el boilerplate asociado con POJO's (Plain Old Java Object) y Beans.
@Entity			// Establece que la clase sea una Entidad.
@Table(name = "Peliculas")
@ApiModel(description = "Detalles sobre Tabla Peliculas")
public class Peliculas {

	/*
	 * @Id El id de la entidad.
	 * @GeneratedValue Establece claves primarias autoincrementale.
	 * @SequenceGenerator Asigna auto un valor de identidad numérico en la interfaz.
	 */
	protected static final String PELICULAS_SEQ = "peliculas_seq";
	@Id		// Specifies that this attribute is the primary key of this entity.
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PELICULAS_SEQ)
	@SequenceGenerator(sequenceName = PELICULAS_SEQ, allocationSize = 1, name = "PELICULAS_SEQ")
	
	/**
	 * Id de entidad Pelicula
	 */
	// @ApiModelProperty añade info del modelo en la interfaz.
	@ApiModelProperty("ID único de cada alumno")
	@Column(name = "ID", updatable = false)
	private Integer id;
	
	/**
	 * Título de entidad Pelicula
	 */
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	/**
	 * Director de entidad Pelicula
	 */
	@Column(name = "DIRECTOR", nullable = false)
	private String director;
	
	/**
	 * Rating de entidad Pelicula
	 */
	@Column(name = "RATING", nullable = false)
	private Float rating;
	
	/**
	 * Género de entidad Pelicula
	 */
	@Column(name = "GENRE", nullable = false)
	private String genre;
	
	/**
	 * Año de creación de entidad Pelicula
	 */
	@Column(name = "YEAR", nullable = false)
	private Integer year;
	
	/**
	 * Pais de entidad Pelicula
	 */
	@Column(name = "COUNTRY", nullable = false)
	private String country;
}
