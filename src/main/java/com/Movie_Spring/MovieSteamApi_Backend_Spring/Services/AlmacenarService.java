package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iAlmacenamientoService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions.BadRequestException;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class AlmacenarService implements iAlmacenamientoService {

    private final static String ALMACENAMIENTO_LOCAL = "mediafile";

    @PostConstruct
    @Override
    public void iniciar() {
        try {
            Files.createDirectories(Paths.get(ALMACENAMIENTO_LOCAL));
        } catch (IOException e) {
            throw new BadRequestException("ERROR ALMACENAMIENTO: Falla al inicializar ruta de almacenamiento!", e);
        }
    }

    @Override
    public String almacenar(MultipartFile archivo) {
        String nombreArchivoOriginal = archivo.getOriginalFilename();
        String nombreArchivo = UUID.randomUUID()+"."+ StringUtils.getFilenameExtension(nombreArchivoOriginal);

        if (archivo.isEmpty()){
            throw new BadRequestException("ERROR VACIO: No es posible almacenar un archivo vacio!");
        }

        try {
            InputStream entrada = archivo.getInputStream();
            Files.copy(entrada, Paths.get(ALMACENAMIENTO_LOCAL).resolve(nombreArchivo), StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw new BadRequestException("ERROR:Falla al almacener el archivo ",e);
        }
        return nombreArchivo;
    }

    @Override
    public Path cargar(String nombreArchivo) {
        return Paths.get(ALMACENAMIENTO_LOCAL).resolve(nombreArchivo);
    }

    @Override
    public Resource cargarRecurso(String nombreArchivo) {
        try {
            Path archivo = cargar(nombreArchivo);
            Resource resource = new UrlResource(archivo.toUri());

            if (resource.exists() || resource.isReadable()){
                return resource;
            }else {
                throw new BadRequestException("ERROR LECTURA: No es posible leer el archivo! " + nombreArchivo);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("ERROR: No se puede leer el archivo! "+nombreArchivo, e);

        }
    }

    @Override
    public void eliminarRecurso(String nombreArchivo) {
        Path archivo = cargar(nombreArchivo);
        try {
            FileSystemUtils.deleteRecursively(archivo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
