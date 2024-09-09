package com.Movie_Spring.MovieSteamApi_Backend_Spring.Services;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.Services.iServices.iUsuarioService;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions.BadRequestException;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.exceptions.ResourceNotFoundException;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.Usuario;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.usuario.UsuarioDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.models.dtos.usuario.UsuarioRegistroDto;
import com.Movie_Spring.MovieSteamApi_Backend_Spring.repository.iUsuarioRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class UsuarioService implements iUsuarioService {

    //String foto = "dummyactualizado.jpg";

    private final iUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioDto> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::converitDtoAUsuario)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UsuarioDto> paginate(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return usuarios.map(this::converitDtoAUsuario);
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Usuario Id: "+id+" no encontrado!"));
    }

    @Override
    public UsuarioDto save(UsuarioRegistroDto usuarioRegistroDto) {

        boolean usuarioExists = usuarioRepository.existsByEmail(usuarioRegistroDto.getEmail());

        if (usuarioExists){
            throw new BadRequestException("ERROR DUPLICADO: EL Email ya existe");
        }

        //Usuario usuario = new Usuario();
        Usuario usuario = new ModelMapper().map(usuarioRegistroDto, Usuario.class);

        try {
          /*  usuario.setNombre(usuarioRegistroDto.getNombre());
            usuario.setEmail(usuarioRegistroDto.getEmail());
            usuario.setCelular(usuarioRegistroDto.getCelular());
            usuario.setRole(usuarioRegistroDto.getRole());
            usuario.setFotoPerfil(usuario.getFotoPerfil());*/

            usuario.setPassword(passwordEncoder.encode(usuarioRegistroDto.getPassword()));
            usuario.setEstado(true);
            usuario.setCreatedAt(LocalDate.now());
            usuarioRepository.save(usuario);
        }catch (DataAccessException e){
            throw new BadRequestException("ERROR CREACION: Falla no es posible realizar el proceso de creación!", e);
        }
        return converitDtoAUsuario(usuario);
    }

    @Override
    public UsuarioDto update(Long id, UsuarioRegistroDto usuarioRegistroDto) {

        Usuario usuario = findById(id);

        boolean usuarioExists = usuarioRepository.existsByEmailAndIdNot(usuarioRegistroDto.getEmail(), id);

        if (usuarioExists){
            throw new BadRequestException("El email ya existe!");
        }

        try {
            if (usuario != null){
                new ModelMapper().map(usuarioRegistroDto, usuario);
//                usuario.setNombre(usuarioRegistroDto.getNombre());
//                usuario.setEmail(usuarioRegistroDto.getEmail());
//                usuario.setCelular(usuarioRegistroDto.getCelular());
//                usuario.setFotoPerfil(usuarioRegistroDto.getFotoPerfil());
                usuario.setUpdatedAt(LocalDate.now());
                usuario.setRole(usuarioRegistroDto.getRole());
                usuario.setPassword(passwordEncoder.encode(usuarioRegistroDto.getPassword()));
            }else{
                throw new BadRequestException("ERROR ACTUALIZACION: no es posible acutalizar el usuario");
            }
        }catch (DataAccessException e){
            throw new BadRequestException("ERROR ACTUALIZACION: Falla no es posible realizar el proceso!" , e);
        }
        usuarioRepository.save(usuario);
        return converitDtoAUsuario(usuario);
    }

    @Override
    public Usuario delete(Long id) {
        Usuario usuario = findById(id);
        try {
            usuario.desactivarUsuario();
        }catch (DataAccessException e){
            throw new BadRequestException("ERROR DELETE: No es posible eliminar el usuario");
        }
        return usuarioRepository.save(usuario);
    }


    private UsuarioDto converitDtoAUsuario(Usuario usuario){
        //UsuarioDto usuarioDto = new UsuarioDto();
        UsuarioDto usuarioDto = new ModelMapper().map(usuario,UsuarioDto.class);
//        usuarioDto.setId(usuario.getId());
//        usuarioDto.setNombre(usuario.getNombre());
//        usuarioDto.setEmail(usuario.getEmail());
//        usuarioDto.setPassword(usuario.getPassword());
//        usuarioDto.setCelular(usuario.getCelular());
//        usuarioDto.setRole(usuario.getRole());
//        usuarioDto.setFotoPerfil(usuario.getFotoPerfil());
//        usuarioDto.setEstado(usuario.getEstado());
//        usuarioDto.setCreatedAt(usuario.getCreatedAt());
//        usuarioDto.setUpdatedAt(usuario.getUpdatedAt());
        return usuarioDto;
    }
}
