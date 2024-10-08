package com.Movie_Spring.MovieSteamApi_Backend_Spring.repository;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iUsuarioRepository extends JpaRepository<Usuario,Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    Optional<Usuario> findByEmail(String email);
}
