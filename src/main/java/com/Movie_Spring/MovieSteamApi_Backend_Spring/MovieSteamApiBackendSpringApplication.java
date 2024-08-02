package com.Movie_Spring.MovieSteamApi_Backend_Spring;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.configuration.ConsumoApiMovieDB;
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
		var json = consumoApiMovieDB.obtenerDatosApi("https://api.themoviedb.org/3/search/tv?query=superman&include_adult=false&language=es-ES&page=1");
		System.out.println(json);
	}
}
