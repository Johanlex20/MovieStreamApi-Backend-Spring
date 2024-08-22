package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.recordConsumoApi;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VideoDto(
        //Datos Video
        @JsonAlias("name") String tituloVideo,
        @JsonAlias("key") String videoKey
) {
}
