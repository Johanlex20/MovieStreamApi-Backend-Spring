package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Episodio;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Temporada;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.EpisodioDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.NombreSerieDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.SerieDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.TemporadaDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iEpisodioRepository;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iSerieRepository;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iTemporadaRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SerieService {

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

    public Serie guardarSerie(NombreSerieDto nombreSerieDto){
        String nombreSerie = nombreSerieDto.nombreSerie();
        SerieDto serieDto = apiService.obtenerDatosSerie(nombreSerie);

        if (serieDto != null){
            SerieDto serieNew = new SerieDto(
                    serieDto.idSerie(),
                    serieDto.titulo(),
                    serieDto.sinopsis(),
                    serieDto.poster(),
                    serieDto.popularidad(),
                    serieDto.numTemporadas(),
                    serieDto.numEpisodiosTotal(),
                    serieDto.genero());
            Serie serie = new Serie(serieNew);
            serie = serieRepository.save(serie);  //Hasta aqui guardamos los datos de la serie


            List<Temporada> temporadas = new ArrayList<>();
            for (int i = 1; i <= serieDto.numTemporadas(); i++){
                TemporadaDto temporadaDto = apiService.obtenerDatosTemporada(serieNew.idSerie(), i);
                Temporada temporada = new Temporada(temporadaDto);
                temporada.setTituloSerie(serie.getTitulo());
                temporada.setSerie(serie);
                temporadas.add(temporada);
            }
            temporadaRepository.saveAll(temporadas);

            for (Temporada temporada : temporadas){
                List<Episodio> episodios = new ArrayList<>();
                TemporadaDto temporadaDto = apiService.obtenerDatosTemporada(serieNew.idSerie(), temporada.getNumeroTemporada());

                for (EpisodioDto episodioDto : temporadaDto.episodios()){
                    Episodio episodio = new Episodio(episodioDto);
                    episodio.setTemporada(temporada);
                    episodio.setTituloSerie(serie.getTitulo());
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
}
