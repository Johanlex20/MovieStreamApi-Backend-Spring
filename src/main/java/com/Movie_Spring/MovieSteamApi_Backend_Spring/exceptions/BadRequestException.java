package com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String string){
        super(string);
    }
    public BadRequestException(String string, Throwable e){
        super(string, e);
    }
}
