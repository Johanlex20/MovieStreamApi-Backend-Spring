package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeneroDto(
        @JsonAlias("name") String genero
) {
}
