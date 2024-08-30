package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iUsuarioService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions.ResourceNotFoundException;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Usuario;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService implements iUsuarioService {

    @Autowired
    private iUsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Page<Usuario> paginate(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Usuario Id: "+id+" no encontrado!"));
    }

    @Override
    public Usuario save(Usuario usuario) {

        String foto = "dummy.jpg";

        Usuario usuarioNew = new Usuario();
        usuarioNew.setNombre(usuario.getNombre());
        usuarioNew.setEmail(usuario.getEmail());
        usuarioNew.setPassword(usuario.getPassword());
        usuarioNew.setCelular(usuario.getCelular());
        usuarioNew.setRole(usuario.getRole());
        usuario.setFotoPerfil(foto);
        usuario.setEstado(true);
        usuario.setCreatedAt(LocalDate.now());
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        String fotoActualizada = "dummyactualizado.jpg";

        Usuario usuarioId = findById(id);

        if (usuarioId != null){
            usuarioId.setNombre(usuario.getNombre());
            usuarioId.setEmail(usuario.getEmail());
            usuarioId.setPassword(usuario.getPassword());
            usuarioId.setCelular(usuario.getCelular());
            usuarioId.setRole(usuario.getRole());
            usuarioId.setFotoPerfil(fotoActualizada);
            usuarioId.setUpdatedAt(LocalDate.now());
        }
        return usuarioRepository.save(usuarioId);
    }

    @Override
    public Usuario delete(Long id) {
        Usuario usuario = findById(id);
        usuario.desactivarUsuario();
        return usuarioRepository.save(usuario);
    }
}
