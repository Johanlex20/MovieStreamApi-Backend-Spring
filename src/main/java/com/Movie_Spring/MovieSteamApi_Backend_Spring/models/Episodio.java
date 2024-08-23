package com.Movie_Spring.MovieSteamApi_Backend_Spring.models;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.recordConsumoApi.EpisodioDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "episodios")
@Data
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numEpisodio;
    private String tituloEpisodio;
    private String fechaEstreno;

    @Column(length = 5000)
    private String sinopsisEpisodio;
    private Integer idEpisodio;
    private Integer numTemporada;
    private String portadaEpisodio;
    private Double promedio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore // Evitar serialización de temporada en episodio
    private Temporada temporada;
    private String tituloSerie;

    @Column(name = "video_key")
    private String videoKey;

    @Column(name = "titulo_video")
    private String tituloVideo;


    public Episodio() {
    }

    public Episodio(EpisodioDto episodioDto) {
        this.numEpisodio = episodioDto.numEpisodio();
        this.tituloEpisodio = episodioDto.tituloEpisodio();
        this.fechaEstreno = episodioDto.fechaEstreno();
        this.sinopsisEpisodio = episodioDto.sinopsisEpisodio();
        this.idEpisodio = episodioDto.idEpisodio();
        this.numTemporada = episodioDto.numTemporada();
        this.portadaEpisodio = episodioDto.portadaEpisodio();
        this.promedio = episodioDto.promedio();
        if (episodioDto.video() != null) {
            this.videoKey = episodioDto.video().videoKey();
            this.tituloVideo = episodioDto.video().tituloVideo();
        } else {
            this.videoKey = null;
            this.tituloVideo = null;
        }
    }
}
