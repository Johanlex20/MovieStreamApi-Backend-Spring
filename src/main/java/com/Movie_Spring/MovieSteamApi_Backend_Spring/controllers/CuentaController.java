package com.Movie_Spring.MovieSteamApi_Backend_Spring.controllers;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions.BadRequestException;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Role;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Usuario;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.autenticacion.PerfilUsuarioDTO;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.usuario.UsuarioRegistroDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class CuentaController {

    private final iUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/registro")
    public PerfilUsuarioDTO registro(@RequestBody @Validated UsuarioRegistroDto usuarioRegistroDTO){
        boolean emailExists = usuarioRepository.existsByEmail(usuarioRegistroDTO.getEmail());
        if (emailExists){
            throw new BadRequestException("ERROR EMAIL DUPLICADO: El email ya existe!");
        }
        Usuario usuario = new Usuario();
        //Usuario usuario = new ModelMapper().map(usuarioRegistroDTO,Usuario.class);
        usuario.setPassword(passwordEncoder.encode(usuarioRegistroDTO.getPassword()));
        usuario.setEstado(Boolean.TRUE);
        usuario.setRole(Role.USER);
        usuario.setCreatedAt(LocalDate.now());
        usuario.setFotoPerfil(usuarioRegistroDTO.getFotoPerfil());
        usuario.setNombre(usuarioRegistroDTO.getNombre());
        usuario.setEmail(usuarioRegistroDTO.getEmail());
        usuario.setCelular(usuarioRegistroDTO.getCelular());

        usuarioRepository.save(usuario);

        return new PerfilUsuarioDTO(usuario.getNombre(),usuario.getEmail(), usuario.getPassword(),usuario.getRole(), usuario.getFotoPerfil());
        //return new ModelMapper().map(usuario, PerfilUsuarioDTO.class);
    }

}
