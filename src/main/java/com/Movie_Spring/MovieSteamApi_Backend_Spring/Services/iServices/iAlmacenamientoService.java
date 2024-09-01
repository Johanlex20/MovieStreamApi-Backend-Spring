package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;

public interface iAlmacenamientoService {
    void iniciar();
    String almacenar(MultipartFile multipartFile);
    Path cargar(String nombreArchivo);
    Resource cargarRecurso(String nombreArchivo);
    void eliminarRecurso(String nombreArchivo);
}
