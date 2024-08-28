package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.serie.SerieDBDto;
import java.util.List;

public interface iHomeService {

    List<SerieDBDto> findByYear(int year) ;
}
