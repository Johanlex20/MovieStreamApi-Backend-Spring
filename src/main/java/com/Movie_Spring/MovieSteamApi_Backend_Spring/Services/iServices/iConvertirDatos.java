package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices;

public interface iConvertirDatos {

    <T> T convertirDatos(String json, Class<T> clase);//metodo generico que converte una cadena de texto en formato JSON en un objeto de una clase específica

}
