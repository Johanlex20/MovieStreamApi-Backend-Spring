package com.Movie_Spring.MovieSteamApi_Backend_Spring.controllers;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.HomeService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.serie.SerieDBDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping(value = "/api/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping(value = "/series/fecha")
    public List<SerieDBDto> getSeriesByYear(@RequestParam int year){
        return homeService.findByYear(year);
    }

    @GetMapping(value = "/series/plataforma")
    public List<SerieDBDto> getSerieByPlataforma(@RequestParam String plataforma){
        return homeService.findByPlataforma(plataforma);
    }

    @GetMapping(value = "/series/last")
    public List<SerieDBDto> getLastSeries(){
        return homeService.getLastSeries();
    }

}
