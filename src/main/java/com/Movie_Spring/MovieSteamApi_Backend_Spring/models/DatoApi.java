package com.Movie_Spring.MovieSteamApi_Backend_Spring.models;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatoApi {
    @JsonAlias("results") List<SerieDto> resultado;

    public List<SerieDto> getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return "resultado=" + resultado;
    }
}
