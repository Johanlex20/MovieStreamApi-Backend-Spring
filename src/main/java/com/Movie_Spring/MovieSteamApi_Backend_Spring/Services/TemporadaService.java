package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iTemporadaService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Temporada;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.Temporada.Temporadas;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iTemporadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TemporadaService implements iTemporadaService {

    @Autowired
    private iTemporadaRepository temporadaRepository;

    @Override
    public List<Temporada> findALL() {
        return temporadaRepository.findAll();
    }

    @Override
    public List<Temporadas> findByTituloSerie(String titulo) {
        List<Temporada> temporadaList = temporadaRepository.findBytituloSerie(titulo);
        return temporadaList.stream()
                .map(this::convertirTemporadasDto)
                .toList();
    }

    @Override
    public Temporada findByTituloSerieAndNumeroTemporada(String titulo, Long numeroTemporada) {
        return temporadaRepository.findByTituloSerieAndNumeroTemporada(titulo, numeroTemporada);
    }


    private Temporadas convertirTemporadasDto(Temporada temporada){
        Temporadas temporadas = new Temporadas();
        temporadas.setId(temporada.getId());
        temporadas.setIdTemporada(temporada.getIdTemporada());
        temporadas.setNombreTemporada(temporada.getNombreTemporada());
        temporadas.setFechaEstrenoTemporada(temporada.getFechaEstrenoTemporada());
        temporadas.setSinopsisTemporada(temporada.getSinopsisTemporada());
        temporadas.setPosterTemporada(temporada.getPosterTemporada());
        temporadas.setNumeroTemporada(temporada.getNumeroTemporada());
        temporadas.setPromedioTemporada(temporada.getPromedioTemporada());
        temporadas.setTituloSerie(temporada.getTituloSerie());
        return temporadas;
    }


}
