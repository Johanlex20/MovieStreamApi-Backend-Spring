package com.Movie_Spring.MovieSteamApi_Backend_Spring.repository;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Temporada;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface iTemporadaRepository extends JpaRepository<Temporada, Long> {

    List<Temporada> findBytituloSerie(String titulo);

    Temporada findByTituloSerieAndNumeroTemporada(String titulo, Long numeroTemporada);

}
