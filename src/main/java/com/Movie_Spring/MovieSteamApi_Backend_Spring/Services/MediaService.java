package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import org.springframework.stereotype.Service;

@Service
public class MediaService {

    private String baseUrlImage = "https://image.tmdb.org/t/p/w500";

    public String cargarImagen (String poster){
        return baseUrlImage + poster;
    }
}
