package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.serie;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Genero;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SerieDBDto {

    private Long id;

    private Long idSerie;

    private String titulo;

    private String sinopsis;

    private String poster;

    private Double popularidad;

    private Integer numTemporadas;

    private Integer numEpisodiosTotal;

    private Genero genero;

}
