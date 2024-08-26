package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iSerieService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Episodio;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Genero;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Temporada;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.recordConsumoApi.*;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.serie.SerieActualizarDTO;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.serie.SerieDBDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iEpisodioRepository;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iSerieRepository;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iTemporadaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService implements iSerieService {

    private final ApiService apiService;
    private final iSerieRepository serieRepository;
    private final iTemporadaRepository temporadaRepository;
    private final iEpisodioRepository episodioRepository;

    public SerieService(ApiService apiService, iSerieRepository serieRepository, iTemporadaRepository temporadaRepository, iEpisodioRepository episodioRepository) {
        this.apiService = apiService;
        this.serieRepository = serieRepository;
        this.temporadaRepository = temporadaRepository;
        this.episodioRepository = episodioRepository;
    }

    @Override
    public List<SerieDBDto> findAll() {
        List<Serie> series = serieRepository.findAll();
        return series.stream()
                .map(this::convertirSerieDtoAObjeto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<SerieDBDto> paginacion(Pageable pageable) {
        Page<Serie> series = serieRepository.findAll(pageable);
        return series.map(this::convertirSerieDtoAObjeto);
    }

    @Override
    public SerieDBDto findById(Long id) {
        Serie serie = serieRepository.findById(id).orElseThrow(()-> new RuntimeException("Serie ID: "+id+" No encontrada"));
        return convertirSerieDtoAObjeto(serie);
    }

    public Serie guardarSerie(NombreSerieDto nombreSerieDto){
        String nombreSerie = nombreSerieDto.nombreSerie();
        SerieDto serieDto = apiService.obtenerDatosSerie(nombreSerie);

        if (serieDto != null){
            Serie serie = new Serie(serieDto);
            serie.setCreatedAt(LocalDateTime.now());
            serie = serieRepository.save(serie);  //Hasta aqui guardamos los datos de la serie

            List<Temporada> temporadas = new ArrayList<>();
            for (int i = 1; i <= serieDto.numTemporadas(); i++){
                TemporadaDto temporadaDto = apiService.obtenerDatosTemporada(serieDto.idSerie(), i);
                Temporada temporada = new Temporada(temporadaDto);
                temporada.setTituloSerie(serie.getTitulo());
                temporada.setSerie(serie);

                VideoDto videoDto = apiService.obtenerVideoTemporada(serieDto.idSerie(), i);
                if (videoDto != null){
                    temporada.setVideoKey(videoDto.videoKey());
                    temporada.setTituloVideo(videoDto.tituloVideo());
                }
                temporadas.add(temporada);
            }
            temporadaRepository.saveAll(temporadas);

            for (Temporada temporada : temporadas){
                List<Episodio> episodios = new ArrayList<>();
                TemporadaDto temporadaDto = apiService.obtenerDatosTemporada(serieDto.idSerie(), temporada.getNumeroTemporada());

                for (EpisodioDto episodioDto : temporadaDto.episodios()){
                    Episodio episodio = new Episodio(episodioDto);
                    episodio.setTemporada(temporada);
                    episodio.setTituloSerie(serie.getTitulo());

                    VideoDto videoDto = apiService.obtenerVideoEpisodio(serieDto.idSerie(), temporada.getNumeroTemporada(), episodio.getNumEpisodio());
                    if (videoDto != null){
                        episodio.setVideoKey(videoDto.videoKey());
                        episodio.setTituloVideo(videoDto.tituloVideo());
                    }
                    episodios.add(episodio);
                }
                episodioRepository.saveAll(episodios);
                temporada.setEpisodios(episodios);
            }
            return serieRepository.save(serie);

        }else {
            return null;
        }
    }

    @Override
    public SerieDBDto actualizarSerie(Long id, SerieActualizarDTO serieActualizarDTO) {

        Serie serie = serieRepository.findById(id).orElseThrow(()->new RuntimeException("Serie no encontrada con Id: " + id));

        serie.setTitulo(serieActualizarDTO.getTitulo());
        serie.setSinopsis(serieActualizarDTO.getSinopsis());
        serie.setPoster(serieActualizarDTO.getPoster());
        serie.setGenero(serieActualizarDTO.getGenero());
        serie.setUpdatedAt(LocalDateTime.now());
        serie = serieRepository.save(serie);
        return convertirSerieDtoAObjeto(serie);
    }

    @Override
    public Boolean eliminarSerie(Long id) {
        serieRepository.deleteById(id);
        return true;
    }

    public SerieDBDto convertirSerieDtoAObjeto(Serie serie){
        SerieDBDto serieDBDto = new SerieDBDto();
        serieDBDto.setId(serie.getId());
        serieDBDto.setIdSerie(serie.getIdSerie());
        serieDBDto.setTitulo(serie.getTitulo());
        serieDBDto.setSinopsis(serie.getSinopsis());
        serieDBDto.setPoster(serie.getPoster());
        serieDBDto.setPopularidad(serie.getPopularidad());
        serieDBDto.setNumTemporadas(serie.getNumTemporadas());
        serieDBDto.setNumEpisodiosTotal(serie.getNumEpisodiosTotal());
        serieDBDto.setGenero(serie.getGenero());
        return serieDBDto;
    }

    public List<Serie> findGenero(Genero genero){
        return serieRepository.findByGenero(genero);
    }
}
