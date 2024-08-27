package com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions;

public class ExternalServiceException extends RuntimeException{
    public ExternalServiceException(String string, Throwable e){
        super(string, e);
    }
}
