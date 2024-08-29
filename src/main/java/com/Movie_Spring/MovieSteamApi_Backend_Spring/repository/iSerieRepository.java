package com.Movie_Spring.MovieSteamApi_Backend_Spring.repository;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Genero;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface iSerieRepository extends JpaRepository<Serie,Long> {

    List<Serie> findByGenero (@Param("genero") Genero genero);

    boolean existsByTitulo(String titulo);

    boolean existsById(Long id);
}
