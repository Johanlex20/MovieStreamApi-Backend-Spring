package com.Movie_Spring.MovieSteamApi_Backend_Spring.controllers;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iUsuarioService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Usuario;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.usuario.UsuarioDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.usuario.UsuarioRegistroDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final iUsuarioService usuarioService;

    @GetMapping(value = "/list")
    private List<UsuarioDto> findAll(){
        return usuarioService.findAll();
    }

    @GetMapping
    public Page<UsuarioDto> paginate(@PageableDefault(sort = "nombre", direction = Sort.Direction.ASC, size = 5) Pageable pageable){
        return usuarioService.paginate(pageable);
    }

    @GetMapping(value = "/{id}")
    public Usuario findById(@PathVariable(value = "id") Long id){
        return usuarioService.findById(id);
    }

    @PostMapping
    public UsuarioDto save(@RequestBody @Valid UsuarioRegistroDto usuarioRegistroDto){
        return usuarioService.save(usuarioRegistroDto);
    }

    @PutMapping(value = "/{id}")
    public UsuarioDto update(@PathVariable(value = "id") Long id,@RequestBody @Valid UsuarioRegistroDto usuarioRegistroDto){
        return usuarioService.update(id, usuarioRegistroDto);
    }

    @DeleteMapping(value = "/{id}")
    public Usuario delete(@PathVariable(value = "id") Long id){
        return usuarioService.delete(id);
    }

}
