package com.Movie_Spring.MovieSteamApi_Backend_Spring.controllers;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iUsuarioService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

    @Autowired
    private iUsuarioService usuarioService;

    @GetMapping(value = "/list")
    private List<Usuario> findAll(){
        return usuarioService.findAll();
    }

    @GetMapping
    public Page<Usuario> paginate(@PageableDefault(sort = "nombre", direction = Sort.Direction.ASC, size = 5) Pageable pageable){
        return usuarioService.paginate(pageable);
    }

    @GetMapping(value = "/{id}")
    public Usuario findById(@PathVariable(value = "id") Long id){
        return usuarioService.findById(id);
    }

    @PostMapping
    public Usuario save(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @PutMapping(value = "/{id}")
    public Usuario update(@PathVariable(value = "id") Long id,@RequestBody Usuario usuario){
        return usuarioService.update(id, usuario);
    }

    @DeleteMapping(value = "/{id}")
    public Usuario delete(@PathVariable(value = "id") Long id){
        return usuarioService.delete(id);
    }

}
