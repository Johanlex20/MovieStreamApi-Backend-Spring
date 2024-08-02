package com.Movie_Spring.MovieSteamApi_Backend_Spring.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SerieDto(
        @JsonAlias("id") Long idSerie,
        @JsonAlias("original_name") String titulo,
        @JsonAlias("overview") String sinopsis,
        @JsonAlias("poster_path") String poster,
        @JsonAlias("popularity") Double popularidad,
        @JsonAlias("number_of_seasons") Integer numTemporadas,
        @JsonAlias("number_of_episodes") Integer numEpisodiosTotal,
        @JsonAlias("genres") List<GeneroDto> genero
) {}