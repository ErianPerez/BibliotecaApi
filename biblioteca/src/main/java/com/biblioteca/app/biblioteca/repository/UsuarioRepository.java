package com.biblioteca.app.biblioteca.repository;


import com.biblioteca.app.biblioteca.model.Prestamo;
import com.biblioteca.app.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNombre(String nombre);
}
