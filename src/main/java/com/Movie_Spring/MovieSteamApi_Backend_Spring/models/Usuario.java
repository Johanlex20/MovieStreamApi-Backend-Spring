package com.Movie_Spring.MovieSteamApi_Backend_Spring.models;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String nombre;

    @Column(length = 65, unique = true)
    private String email;

    private String password;
    private String celular;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String fotoPerfil;
    private Boolean estado;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public void desactivarUsuario(){
        this.estado = false;
    }

}
