package com.Movie_Spring.MovieSteamApi_Backend_Spring.handler;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions.BadRequestException;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions.DataBaseException;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions.ExternalServiceException;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.net.URI;
import java.util.*;

@RestControllerAdvice
public class RestHandlerException {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handlerValidationErrors(MethodArgumentNotValidException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("ERROR DE ARGUMENTOS: Metodo no valido!");
        problemDetail.setType(URI.create("https://Good_Projects.com/error/Argument_not_valid"));
        problemDetail.setDetail("ERROR: No es posible realizar la consulta ya que tiene errores!");

        Set<String> errors = new HashSet<>();
        List<FieldError> fieldErrors = ex.getFieldErrors();

        for (FieldError fiel:fieldErrors){
            String messages = messageSource.getMessage(fiel, Locale.getDefault());
            errors.add(messages);
        }
        problemDetail.setProperty("errors", errors);
        return problemDetail;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    ProblemDetail handlerResourceNotFoundException(ResourceNotFoundException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("SOLICITUD NO ENCONTRADA");
        problemDetail.setType(URI.create("https://Good_Projects.com/error/not_found"));
        return problemDetail;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    ProblemDetail handlerBadRequestException(BadRequestException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("SOLICITUD INCORRECTA");
        problemDetail.setType(URI.create("https://Good_Projects.com/error/bad_request"));
        return problemDetail;
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(ExternalServiceException.class)
    ProblemDetail handlerExternalServiceException(ExternalServiceException ex){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY, ex.getMessage());
        problemDetail.setTitle("ERROR EN EL SERVICIO EXTERNO");
        problemDetail.setType(URI.create("https://Good_Projects.com/error/external_service"));
        return problemDetail;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataBaseException.class)
    ProblemDetail handlerDataBaseException(DataBaseException ex){
        ProblemDetail problemDetail =ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        problemDetail.setTitle("ERROR DE BASE DE DATOS");
        problemDetail.setType(URI.create("https://Good_Projects.com/error/database_error"));
        return problemDetail;
    }

}
