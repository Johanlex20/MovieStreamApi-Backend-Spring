package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Temporada;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.Temporada.Temporadas;
import java.util.List;
import java.util.Optional;

public interface iTemporadaService {
    List<Temporada> findALL();

    List<Temporadas> findByTituloSerie(String titulo);

    Temporada findByTituloSerieAndNumeroTemporada(String titulo, Long numeroTemporada);
}