package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.NombreSerieDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.SerieDBDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface iSerieService {
    List<SerieDBDto> findAll();
    Page<Serie> paginacion(Pageable pageable);
    Serie findById(Long id);
    Serie guardarSerie(NombreSerieDto nombreSerieDto);
    Serie actualizarSerie(Long id, Serie serie);
    Boolean eliminarSerie(Long id);
}
