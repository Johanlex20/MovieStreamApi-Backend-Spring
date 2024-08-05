package com.Movie_Spring.MovieSteamApi_Backend_Spring.repository;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iSerieRepository extends JpaRepository<Serie,Long> {
}
