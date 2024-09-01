package com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.autenticacion;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioDTO {

    private Long id;
    private String nombre;
    private String email;
    private String password;
    private Role role;
    private String fotoDePerfil;

}
