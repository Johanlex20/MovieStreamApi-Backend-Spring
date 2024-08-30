package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.usuario;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Role;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioRegistroDto {

    @NotNull
    @NotBlank
    @Size(min = 3, message = "Nombre debe tener almenos 3 caracteres!")
    @Size(max = 45, message = "Nombre puede tener maximo 45 caracteres!")
    private String nombre;

    @NotNull
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "[a-z0-9-]+")
    @Size(min = 5, message = "El password debe tener al menos 5 caracteres!")
    private String password;

    @NotNull
    @NotBlank
    private String celular;

    @NotNull
    private Role role;

    //@NotEmpty
    private String fotoPerfil;

}
