package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.serie;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Genero;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.time.LocalDate;

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

    private LocalDate fechaLanzamientoSerie;

    private String plataforma;

    private Double promedioVotos;

    public SerieDBDto() {
    }

    public SerieDBDto(Long id, Long idSerie, String titulo, String sinopsis,  String poster, Double popularidad, Integer numTemporadas, Integer numEpisodiosTotal, Genero genero, String videoKey,String tituloVideo, LocalDate fechaLanzamientoSerie, String plataforma, Double promedioVotos) {
        this.id = id;
        this.idSerie = idSerie;
        this.titulo = titulo;
        this.sinopsis = sinopsis;
        this.poster = poster;
        this.popularidad = popularidad;
        this.numTemporadas = numTemporadas;
        this.numEpisodiosTotal = numEpisodiosTotal;
        this.genero = genero;
        this.videoKey = videoKey;
        this.tituloVideo =tituloVideo;
        this.fechaLanzamientoSerie = fechaLanzamientoSerie;
        this.plataforma = plataforma;
        this.promedioVotos = promedioVotos;

    }

    public SerieDBDto(Serie serie) {
        this.id = serie.getId();
        this.idSerie = serie.getIdSerie();
        this.titulo = serie.getTitulo();
        this.sinopsis = serie.getSinopsis();
        this.poster = serie.getPoster();
        this.popularidad = serie.getPopularidad();
        this.numTemporadas = serie.getNumTemporadas();
        this.numEpisodiosTotal = serie.getNumEpisodiosTotal();
        this.genero = serie.getGenero();
        this.videoKey = serie.getVideoKey();
        this.tituloVideo = serie.getTituloVideo();
        this.fechaLanzamientoSerie = serie.getFechaLanzamientoSerie();
        this.plataforma = serie.getPlataforma();
        this.promedioVotos = serie.getPromedioVotos();
    }



}
