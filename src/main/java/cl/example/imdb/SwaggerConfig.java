package cl.example.imdb;


import static springfox.documentation.builders.PathSelectors.regex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Clase principal de configuración del Swagger.
 * 
 * @author julio
 * @version 1.0
 * @since 15/01/2021
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/**
	 * Constructo de la interfaz primaria del framework Springfox,
	 * provee las configuraciones, setea la URL base y contruye la interfaz.
	 * @return Docket() 
	 */
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)		// 
                .apiInfo(apiInfo())
                .select()
                .paths(regex("/imdb.*"))
                .build();
    }
    
    /**
     * Sete la Metadata pe la API.
     * @return ApiInfoBuilder()
     */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Imdb API")
				.description("ApiRest of movies and series")
                .version("0.1")
				.build();
	}
    
}
