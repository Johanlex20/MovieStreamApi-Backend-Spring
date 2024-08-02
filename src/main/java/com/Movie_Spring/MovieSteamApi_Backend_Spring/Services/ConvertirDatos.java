package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iConvertirDatos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConvertirDatos implements iConvertirDatos {
    private ObjectMapper objectMapper =new ObjectMapper();

    @Override
    public <T> T convertirDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
