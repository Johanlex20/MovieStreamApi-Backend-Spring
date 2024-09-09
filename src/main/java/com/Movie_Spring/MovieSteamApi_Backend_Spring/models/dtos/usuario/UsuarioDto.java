package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.usuario;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDto {

    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String celular;
    private Role role;
    private String fotoPerfil;
    private Boolean estado;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
