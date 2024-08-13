package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TemporadaDto(
        @JsonAlias("id") Integer idTemporada,
        @JsonAlias("original_name") String tituloSerie,
        @JsonAlias("name") String nombreTemporada,
        @JsonAlias("air_date") String fechaEstrenoTemporada,
        @JsonAlias("overview") String sinopsisTemporada,
        @JsonAlias("poster_path") String posterTemporada,
        @JsonAlias("season_number") Integer numeroTemporada,
        @JsonAlias("vote_average") Double promedioTemporada,
        @JsonAlias("episodes") List<EpisodioDto> episodios

) {}
