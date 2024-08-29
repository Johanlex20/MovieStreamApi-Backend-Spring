package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iHomeService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Genero;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.serie.SerieDBDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iHomeRepository;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iSerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService implements iHomeService {

    @Autowired
    private iHomeRepository homeRepository;

    @Autowired
    private iSerieRepository serieRepository;

    @Override
    public List<SerieDBDto> findByYear(int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1); // Primer día del año
        LocalDate endDate = LocalDate.of(year, 12, 31); // Último día del año
        return homeRepository.findByYear(startDate, endDate)
                .stream()
                .map(SerieDBDto::new)
                .collect(Collectors.toList());
    }

    public List<SerieDBDto> findGenero(Genero genero){
        return serieRepository.findByGenero(genero)
                .stream()
                .map(serie -> new SerieDBDto(
                        serie.getId(),
                        serie.getIdSerie(),
                        serie.getTitulo(),
                        /*serie.getSinopsis(),*/
                        serie.getPoster(),
                        serie.getPopularidad(),
                        serie.getNumTemporadas(),
                        serie.getNumEpisodiosTotal(),
                        serie.getGenero(),
                        serie.getTituloVideo(),
                        serie.getVideoKey(),
                        serie.getFechaLanzamientoSerie(),
                        serie.getPlataforma()
                )).collect(Collectors.toList());
    }

    public List<SerieDBDto> findByPlataforma(String plataforma){
        return homeRepository.findByPlataforma(plataforma)
                .stream()
                .map(serie -> new SerieDBDto(
                        serie.getId(),
                        serie.getIdSerie(),
                        serie.getTitulo(),
                        /*serie.getSinopsis(),*/
                        serie.getPoster(),
                        serie.getPopularidad(),
                        serie.getNumTemporadas(),
                        serie.getNumEpisodiosTotal(),
                        serie.getGenero(),
                        serie.getTituloVideo(),
                        serie.getVideoKey(),
                        serie.getFechaLanzamientoSerie(),
                        serie.getPlataforma()
                )).collect(Collectors.toList());
    }

}
