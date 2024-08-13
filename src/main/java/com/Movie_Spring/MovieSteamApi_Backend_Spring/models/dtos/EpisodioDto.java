package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodioDto(
        @JsonAlias("episode_number") Integer numEpisodio,
        @JsonAlias("name") String tituloEpisodio,
        @JsonAlias("air_date") String fechaEstreno,
        @JsonAlias("overview") String sinopsisEpisodio,
        @JsonAlias("id") Integer idEpisodio,
        @JsonAlias("season_number") Integer numTemporada,
        @JsonAlias("still_path") String portadaEpisodio,
        @JsonAlias("vote_average") Double promedio
) {}
