package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.recordConsumoApi;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PlataformaVideoDto(
        @JsonAlias("name") String plataforma
) {}
