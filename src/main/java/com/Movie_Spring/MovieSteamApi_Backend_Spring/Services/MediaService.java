package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import org.springframework.stereotype.Service;

@Service
public class MediaService {

    private String baseUrlImage = "https://image.tmdb.org/t/p/w500";
    private String baseUrlVideo = "https://www.youtube.com/watch?v=";

    public String cargarImagen (String poster){
        return baseUrlImage + poster;
    }

    public String cargarVideo (String videoKey){
        return baseUrlVideo + videoKey;
    }
}
