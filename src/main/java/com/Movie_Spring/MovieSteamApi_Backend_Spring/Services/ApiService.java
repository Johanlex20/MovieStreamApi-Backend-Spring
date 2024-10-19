package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.configuration.ConsumoApiMovieDB;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.DatoApi;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.recordConsumoApi.*;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    private final String  API_BASE = "https://api.themoviedb.org/3/";
    private final String API_IDIOMA_ES = "language=es-ES";
    ConsumoApiMovieDB consumoApiMovieDB = new ConsumoApiMovieDB();
    ConvertirDatos convertirDatos = new ConvertirDatos();

    public SerieDto obtenerDatosSerie(String nombreSerie){
        //OBTNER LOS DATOS TAL CUAL LLEGAN DEL API EXTERNA
        String json = consumoApiMovieDB.obtenerDatosApi(API_BASE + "search/tv?query=" + nombreSerie.replace(" ", "%20") +"&include_adult=false&"+ API_IDIOMA_ES);
        //CONVERTIR LOS DATOS EXTERNOS A DATOS JAVA
        DatoApi datos = convertirDatos.convertirDatos(json, DatoApi.class);


        //OBTENER SOLO EL PRIMER RESLUTADO DEL GRUPO DE DATOS VERIFICAMOS QUE NO SEA NULL Y NO ESTE VACIO, ESTA PARTE SE DEBE HACER PARA OBTENER EL ID DE LA SERIE YA QUE CON ESTE VAMOS A OBTENER MAS DATOS CON OTRO URL
        if (datos.getResultado() != null && !datos.getResultado().isEmpty()){
            SerieDto primerDato = datos.getResultado().get(0);
            json = consumoApiMovieDB.obtenerDatosApi(API_BASE +"tv/" + primerDato.idSerie() + "?" + API_IDIOMA_ES);
            SerieDto serieDetalles = convertirDatos.convertirDatos(json, SerieDto.class);
            //OBJETO VIDEO SEGUN ID
            VideoDto videoDto = obtenerVideoSerie(primerDato.idSerie());

            // Actualizar SerieDto con video
            return new SerieDto(
                    serieDetalles.idSerie(),
                    serieDetalles.titulo(),
                    serieDetalles.sinopsis(),
                    serieDetalles.poster(),
                    serieDetalles.popularidad(),
                    serieDetalles.numTemporadas(),
                    serieDetalles.numEpisodiosTotal(),
                    serieDetalles.genero(),
                    videoDto,
                    serieDetalles.fechaLanzamientoSerie(),
                    serieDetalles.plataforma(),
                    serieDetalles.promedioVotos()
            );
        }
        return null;
    }

    //OBTENER VIDEO PARA EL OBJETO SERIE
    public VideoDto obtenerVideoSerie(Long idSerie){
        String videoJson = consumoApiMovieDB.obtenerDatosApi(API_BASE + "tv/" + idSerie + "/videos");
        VideoRespuesta videoRespuesta = convertirDatos.convertirDatos(videoJson, VideoRespuesta.class);
        //System.out.println("videoRespuesta = " + videoRespuesta);

        if (videoRespuesta.results() != null && !videoRespuesta.results().isEmpty()){
            return videoRespuesta.results().get(0);
        }
        return new VideoDto(
                null,null
        );
    }

    public TemporadaDto obtenerDatosTemporada(Long idSerie, int numeroTemporada){
        String json = consumoApiMovieDB.obtenerDatosApi(API_BASE + "tv/" + idSerie + "/season/" + numeroTemporada + "?" + API_IDIOMA_ES);
        return convertirDatos.convertirDatos(json, TemporadaDto.class);
    }

    //OBTENER VIDEO PARA EL OBJETO SERIE
    public VideoDto obtenerVideoTemporada(Long idSerie, Integer numeroTemporada){
        String videoJson = consumoApiMovieDB.obtenerDatosApi(API_BASE + "tv/" + idSerie + "/season/" + numeroTemporada + "/videos");
        VideoRespuesta videoRespuesta = convertirDatos.convertirDatos(videoJson, VideoRespuesta.class);
        //System.out.println("videoRespuesta = " + videoRespuesta);

        if (videoRespuesta.results() != null && !videoRespuesta.results().isEmpty()) {
            return videoRespuesta.results().get(0); // Devuelve el primer video
        } else {
            return new VideoDto(null,null); // No hay videos disponibles
        }
    }

    public VideoDto obtenerVideoEpisodio(Long idSerie, int numeroTemporada, int numeroEpisodio){
        String json = consumoApiMovieDB.obtenerDatosApi(API_BASE + "tv/" + idSerie + "/season/" + numeroTemporada + "/episode/" + numeroEpisodio + "/videos");
        VideoRespuesta videoRespuesta = convertirDatos.convertirDatos(json, VideoRespuesta.class);
        if (videoRespuesta.results() != null && !videoRespuesta.results().isEmpty()) {
            return videoRespuesta.results().get(0); // Devuelve el primer video
        } else {
            return new VideoDto(null,null); // No hay videos disponibles
        }
    }
}
