package com.Movie_Spring.MovieSteamApi_Backend_Spring.repository;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface iHomeRepository extends JpaRepository<Serie,Long> {

    // Consulta  JPQL buscar series por año
    @Query("SELECT e FROM Serie e WHERE e.fechaLanzamientoSerie BETWEEN :startDate AND :endDate")
    List<Serie> findByYear(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Serie> findByPlataforma(@Param("plataforma") String plataforma);

    List<Serie> findTop5ByOrderByCreatedAtDesc();

}
