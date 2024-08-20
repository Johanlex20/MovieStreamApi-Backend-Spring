package com.Movie_Spring.MovieSteamApi_Backend_Spring.controllers;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iTemporadaService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Temporada;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.Temporada.Temporadas;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iTemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/temporada")
public class TemporadaController {

    @Autowired
    private iTemporadaService temporadaService;

    @Autowired
    private iTemporadaRepository temporadaRepository;

    @GetMapping(value = "/list")
    private List<Temporada> findALL(){
        return temporadaService.findALL();
    }

    @GetMapping("/temporadas/{titulo}")
    List<Temporadas>findBytituloSerie(@PathVariable(value = "titulo") String titulo){
        return temporadaService.findByTituloSerie(titulo);
    }

}
