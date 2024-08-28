package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iHomeService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.serie.SerieDBDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService implements iHomeService {

    @Autowired
    private iHomeRepository homeRepository;

    @Override
    public List<SerieDBDto> findByYear(int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1); // Primer día del año
        LocalDate endDate = LocalDate.of(year, 12, 31); // Último día del año
        return homeRepository.findByYear(startDate, endDate)
                .stream()
                .map(SerieDBDto::new)
                .collect(Collectors.toList());
    }

}
