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

    private String videoKey;

    private String tituloVideo;

    public SerieDBDto() {
    }

    public SerieDBDto(Long id, Long idSerie, String titulo, /*Stringsinopsis,*/  String poster, Double popularidad, Integer numTemporadas, Integer numEpisodiosTotal, Genero genero, String videoKey) {
        this.id = id;
        this.idSerie = idSerie;
        this.titulo = titulo;
        //this.sinopsis = sinopsis;
        this.poster = poster;
        this.popularidad = popularidad;
        this.numTemporadas = numTemporadas;
        this.numEpisodiosTotal = numEpisodiosTotal;
        this.genero = genero;
        this.videoKey = videoKey;

    }
}
