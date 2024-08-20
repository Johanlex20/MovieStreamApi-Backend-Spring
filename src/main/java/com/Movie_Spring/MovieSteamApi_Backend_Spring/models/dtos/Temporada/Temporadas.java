package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.Temporada;
import lombok.Data;
@Data
public class Temporadas {

    private Long id;
    private Integer idTemporada;
    private String nombreTemporada;
    private String fechaEstrenoTemporada;
    private String sinopsisTemporada;
    private String posterTemporada;
    private Integer numeroTemporada;
    private Double promedioTemporada;
    private String tituloSerie;
}
