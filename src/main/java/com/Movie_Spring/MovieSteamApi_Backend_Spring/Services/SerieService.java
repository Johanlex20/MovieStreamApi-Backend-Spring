package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.NombreSerieDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.SerieDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iSerieRepository;
import org.springframework.stereotype.Service;

@Service
public class SerieService {

    private final ApiService apiService;
    private final iSerieRepository serieRepository;

    public SerieService(ApiService apiService, iSerieRepository serieRepository) {
        this.apiService = apiService;
        this.serieRepository = serieRepository;
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
            return serieRepository.save(serie);
        }else {
            return null;
        }

    }
}
