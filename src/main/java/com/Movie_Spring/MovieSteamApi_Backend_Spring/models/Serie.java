package com.Movie_Spring.MovieSteamApi_Backend_Spring.models;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.recordConsumoApi.GeneroDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.recordConsumoApi.PlataformaVideoDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.recordConsumoApi.SerieDto;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "series")
@Data
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idSerie;

    @Column(unique = true)
    private String titulo;

    @Column(length = 50000)
    private String sinopsis;

    private String poster;

    private Double popularidad;

    private Integer numTemporadas;

    private Integer numEpisodiosTotal;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    private List<Temporada> temporadas;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "video_key")
    private String videoKey;

    @Column(name = "titulo_video")
    private String tituloVideo;

    private LocalDate fechaLanzamientoSerie;

    private String plataforma;

    public Serie() {
    }

    public Serie(SerieDto serieDto) {
        this.idSerie = serieDto.idSerie();
        this.titulo = serieDto.titulo();
        this.sinopsis = serieDto.sinopsis();
        this.poster = serieDto.poster();
        this.popularidad = serieDto.popularidad();
        this.numTemporadas = serieDto.numTemporadas();
        this.numEpisodiosTotal = serieDto.numEpisodiosTotal();
        this.genero = serieDto.genero()
                .stream()
                .map(GeneroDto::genero)
                .findFirst()
                .map(g -> {
                    try {
                        return Genero.fromString(g);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    }).orElse(null);
        this.tituloVideo = serieDto.video().tituloVideo();
        this.videoKey = serieDto.video().videoKey();
        this.fechaLanzamientoSerie = LocalDate.parse(serieDto.fechaLanzamientoSerie());
        this.plataforma = serieDto.plataforma()
                .stream()
                .map(PlataformaVideoDto::plataforma)
                .findFirst().orElse(null);
    }

}
