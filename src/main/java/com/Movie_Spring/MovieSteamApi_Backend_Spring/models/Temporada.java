package com.Movie_Spring.MovieSteamApi_Backend_Spring.models;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.recordConsumoApi.TemporadaDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "temporadas")
@Data
public class Temporada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer idTemporada;
    private String nombreTemporada;
    private String fechaEstrenoTemporada;

    @Column(length = 5000)
    private String sinopsisTemporada;
    private String posterTemporada;
    private Integer numeroTemporada;
    private Double promedioTemporada;

    @ManyToOne
    @JsonIgnore // Evitar serialización de la serie en la temporada
    private Serie serie;

    private String tituloSerie;

    @OneToMany(mappedBy = "temporada", cascade = CascadeType.ALL)
    private List<Episodio> episodios;

    public Temporada() {
    }

    public Temporada(TemporadaDto temporadaDto) {
        this.idTemporada = temporadaDto.idTemporada();
        this.tituloSerie = temporadaDto.tituloSerie();
        this.nombreTemporada = temporadaDto.nombreTemporada();
        this.fechaEstrenoTemporada = temporadaDto.fechaEstrenoTemporada();
        this.sinopsisTemporada = temporadaDto.sinopsisTemporada();
        this.posterTemporada = temporadaDto.posterTemporada();
        this.numeroTemporada = temporadaDto.numeroTemporada();
        this.promedioTemporada = temporadaDto.promedioTemporada();

    }
}
