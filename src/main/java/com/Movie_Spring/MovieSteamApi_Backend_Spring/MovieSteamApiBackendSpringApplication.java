package com.Movie_Spring.MovieSteamApi_Backend_Spring;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.ConvertirDatos;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.configuration.ConsumoApiMovieDB;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.DatoApi;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.SerieDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieSteamApiBackendSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MovieSteamApiBackendSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("PROBANDO API");

		ConsumoApiMovieDB consumoApiMovieDB = new ConsumoApiMovieDB();
		ConvertirDatos convertirDatos = new ConvertirDatos();

		//OBTNER LOS DATOS TAL CUAL LLEGAN DEL API EXTERNA
		var json = consumoApiMovieDB.obtenerDatosApi("https://api.themoviedb.org/3/search/tv?query=Friends&include_adult=false&language=es-ES");
		System.out.println(json);

		//CONVERTIR LOS DATOS EXTERNOS A DATOS JAVA
		var datos = convertirDatos.convertirDatos(json, DatoApi.class);
		//System.out.println(datos);

		//OBTENER SOLO EL PRIMER RESLUTADO DEL GRUPO DE DATOS VERIFICAMOS QUE NO SEA NULL Y NO ESTE VACIO
		//ESTA PARTE SE DEBE HACER PARA OBTENER EL ID DE LA SERIE YA QUE CON ESTE VAMOS A OBTENER MAS DATOS CON OTRO URL
		if (datos.getResultado() != null && !datos.getResultado().isEmpty()){
			SerieDto primerDato = datos.getResultado().get(0);
			System.out.println("id Serie: "+ primerDato.idSerie() +" titulo:" + primerDato.titulo());
		}

	}




}



