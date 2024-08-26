package com.Movie_Spring.MovieSteamApi_Backend_Spring.controllers;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping(value = "/api/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @GetMapping("/image")
    public RedirectView getImageUrl(@RequestParam String poster){
        String img = mediaService.cargarImagen(poster);
        return new RedirectView(img);
    }

    @GetMapping(value = "/video")
    public RedirectView getVideoUrl(@RequestParam String videoKey){
        String video = mediaService.cargarVideo(videoKey);
        return new RedirectView(video);
    }

}
