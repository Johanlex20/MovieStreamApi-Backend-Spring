package com.Movie_Spring.MovieSteamApi_Backend_Spring.controllers;

import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iEpisodioService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Episodio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/episodio")
public class EpisodioController {

    @Autowired
    private iEpisodioService episodioService;

    @GetMapping("/{id}")
    public Episodio episodioFindById(@PathVariable(value = "id") Long id) {
        return episodioService.episodioFindById(id);
    }
}
