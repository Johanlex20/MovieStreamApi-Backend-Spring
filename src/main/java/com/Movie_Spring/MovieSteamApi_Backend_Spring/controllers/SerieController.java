package com.Movie_Spring.MovieSteamApi_Backend_Spring.controllers;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.SerieService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.NombreSerieDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @PostMapping("/buscar")
    public Serie buscarSerie(@RequestBody NombreSerieDto nombreSerieDto){
        return serieService.guardarSerie(nombreSerieDto);
    }
}
