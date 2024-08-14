package com.Movie_Spring.MovieSteamApi_Backend_Spring.controllers;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.SerieService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iSerieService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.NombreSerieDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.SerieDBDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/series")
public class SerieController {

    @Autowired
    private iSerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @PostMapping("/buscar")
    public Serie buscarSerie(@RequestBody NombreSerieDto nombreSerieDto){
        return serieService.guardarSerie(nombreSerieDto);
    }

    @GetMapping("/list")
    public List<SerieDBDto> findAll(){
        return serieService.findAll();
    }

    @GetMapping
    public Page<SerieDBDto> paginacion(@PageableDefault(sort = "titulo", direction = Sort.Direction.ASC, size = 2) Pageable pageable){
        return serieService.paginacion(pageable);
    }
}
