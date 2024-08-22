package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.recordConsumoApi;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VideoRespuesta(
        @JsonAlias("results") List<VideoDto> results
) {}
