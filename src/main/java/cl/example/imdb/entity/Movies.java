package cl.example.imdb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "Peliculas",
	uniqueConstraints = {
		@UniqueConstraint(name = "pelicula_title_unique", columnNames = "title")	// unique values
	}
)
@ApiModel(description = "Detalles sobre Tabla Peliculas")
public class Movies {

	/*
	 * @Id El id de la entidad.
	 * @GeneratedValue Establece claves primarias autoincrementale.
	 * @SequenceGenerator Asigna auto un valor de identidad numérico en la interfaz.
	 */
	protected static final String PELICULAS_SEQ = "peliculas_seq";
	
	/**
	 * Id de entidad Pelicula
	 */
	// @ApiModelProperty añade info del modelo en la interfaz.
	@Id		// Specifies that this attribute is the primary key of this entity.
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PELICULAS_SEQ)
	@SequenceGenerator(sequenceName = PELICULAS_SEQ, allocationSize = 1, name = "PELICULAS_SEQ")
	@ApiModelProperty("ID único de cada pelicula")
	private Integer id;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "DIRECTOR")
	private String director;
	
	@Column(name = "RATING")
	private Float rating;
	
	@Column(name = "GENRE")
	private String genre;
	
	@Column(name = "YEAR")
	private Integer year;
	
	@Column(name = "DURATION")
	private String duration;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "IMAGE")
	private String image;
	
	@Column(name = "LANGUAGE")
	private String language;
}
