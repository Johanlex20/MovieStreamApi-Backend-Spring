package com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions;

public class DataBaseException extends RuntimeException{
    public DataBaseException(String string, Throwable e){
        super(string, e);
    }
}
