package com.Movie_Spring.MovieSteamApi_Backend_Spring.controllers;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.SerieService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iHomeService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iSerieService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Genero;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.recordConsumoApi.NombreSerieDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.serie.SerieActualizarDTO;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.serie.SerieDBDto;
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

    @Autowired
    private iHomeService homeService;

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

    @GetMapping("/{id}")
    public SerieDBDto findById(@PathVariable(value = "id") Long id){
        return serieService.findById(id);
    }

    @PutMapping(value = "/{id}")
    public SerieDBDto actualizarSerie(@PathVariable(value = "id") Long id, @RequestBody SerieActualizarDTO serieActualizarDTO){
        return serieService.actualizarSerie(id,serieActualizarDTO);
    }

    @DeleteMapping(value = "/{id}")
    public Boolean eliminarSerie(@PathVariable(value = "id") Long id){
        return serieService.eliminarSerie(id);
    }

    @GetMapping("/genero")
    public List<SerieDBDto> findGenero(@RequestParam(value = "genero") Genero genero){
       return homeService.findGenero(genero);
    }
}
