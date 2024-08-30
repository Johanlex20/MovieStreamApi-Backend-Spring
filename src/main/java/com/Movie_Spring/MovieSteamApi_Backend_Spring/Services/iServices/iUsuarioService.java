package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Usuario;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.usuario.UsuarioDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.usuario.UsuarioRegistroDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface iUsuarioService {
    List<UsuarioDto> findAll();
    Page<UsuarioDto> paginate(Pageable pageable);
    Usuario findById(Long id);
    UsuarioDto save(UsuarioRegistroDto usuarioRegistroDto);
    UsuarioDto update(Long id, UsuarioRegistroDto usuarioRegistroDto);
    Usuario delete(Long id);
}
