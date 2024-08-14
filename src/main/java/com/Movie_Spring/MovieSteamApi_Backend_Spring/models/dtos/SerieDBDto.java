package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Genero;
import lombok.Data;

@Data
public class SerieDBDto {

    private Long idSerie;

    private String titulo;

    private String sinopsis;

    private String poster;

    private Double popularidad;

    private Integer numTemporadas;

    private Integer numEpisodiosTotal;

    private Genero genero;

}
