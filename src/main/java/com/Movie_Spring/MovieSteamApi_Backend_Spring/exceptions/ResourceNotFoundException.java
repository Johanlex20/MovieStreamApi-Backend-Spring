package com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {}

    public ResourceNotFoundException(String string) {
        super(string);
    }
}
