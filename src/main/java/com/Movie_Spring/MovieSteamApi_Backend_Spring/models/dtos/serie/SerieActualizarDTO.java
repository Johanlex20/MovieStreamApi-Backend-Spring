package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.serie;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Genero;
import lombok.Data;

@Data
public class SerieActualizarDTO {

    private String nombreSerie;

    private String sinopsis;

    //private String poster;

    private Genero genero;

    private Double popularidad;

    private Integer numTemporadas;

    private Integer numEpisodiosTotal;

    private String videoKey;

    private String plataforma;

}
