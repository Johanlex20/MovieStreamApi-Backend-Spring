package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iEpisodioService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions.ResourceNotFoundException;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Episodio;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iEpisodioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpisodioService implements iEpisodioService {

    @Autowired
    private iEpisodioRepository episodioRepository;

    @Override
    public Episodio episodioFindById(Long id) {
        return episodioRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Episodio No encontrado"));
    }
}
