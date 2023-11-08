package com.biblioteca.app.biblioteca.service;

import com.biblioteca.app.biblioteca.model.Prestamo;
import com.biblioteca.app.biblioteca.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface UsuarioService {
    Usuario findById(Long id);

    List<Usuario> findAll();

    Usuario save(Usuario usuario);

    void deleteUsuario(Usuario libro);

    Usuario findByNombre(String nombre);
}
