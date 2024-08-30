package com.Movie_Spring.MovieSteamApi_Backend_Spring.repository;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface iUsuarioRepository extends JpaRepository<Usuario,Long> {
}
