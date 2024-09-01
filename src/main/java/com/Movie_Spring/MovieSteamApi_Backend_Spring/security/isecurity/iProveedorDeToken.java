package com.Movie_Spring.MovieSteamApi_Backend_Spring.security.isecurity;

import org.springframework.security.core.Authentication;

public interface iProveedorDeToken {
    String crearToken(Authentication authentication);
    Authentication obtenerAuthenticacion(String token);
    boolean validacionToken(String token);
}
