package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.configuration.ConsumoApiMovieDB;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.DatoApi;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.SerieDto;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    private final String  API_BASE = "https://api.themoviedb.org/3/";
    private final String API_IDIOMA_ES = "language=es-ES";
    ConsumoApiMovieDB consumoApiMovieDB = new ConsumoApiMovieDB();
    ConvertirDatos convertirDatos = new ConvertirDatos();

    public SerieDto obtenerDatosSerie(String nombreSerie){
        //OBTNER LOS DATOS TAL CUAL LLEGAN DEL API EXTERNA
        var json = consumoApiMovieDB.obtenerDatosApi(API_BASE + "search/tv?query=" + nombreSerie.replace(" ", "%20") +"&include_adult=false&"+ API_IDIOMA_ES);

        //CONVERTIR LOS DATOS EXTERNOS A DATOS JAVA
        var datos = convertirDatos.convertirDatos(json, DatoApi.class);


        //OBTENER SOLO EL PRIMER RESLUTADO DEL GRUPO DE DATOS VERIFICAMOS QUE NO SEA NULL Y NO ESTE VACIO, ESTA PARTE SE DEBE HACER PARA OBTENER EL ID DE LA SERIE YA QUE CON ESTE VAMOS A OBTENER MAS DATOS CON OTRO URL
        if (datos.getResultado() != null && !datos.getResultado().isEmpty()){
            SerieDto primerDato = datos.getResultado().get(0);
            json = consumoApiMovieDB.obtenerDatosApi(API_BASE +"tv/" + primerDato.idSerie() + "?" + API_IDIOMA_ES);
            return convertirDatos.convertirDatos(json, SerieDto.class);
        }
        return null;
    }
}
