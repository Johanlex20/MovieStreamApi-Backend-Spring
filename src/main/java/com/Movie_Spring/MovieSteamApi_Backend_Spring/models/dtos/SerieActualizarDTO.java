package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Genero;
import lombok.Data;

@Data
public class SerieActualizarDTO {

    private String titulo;

    private String sinopsis;

    private String poster;

    private Genero genero;
}
