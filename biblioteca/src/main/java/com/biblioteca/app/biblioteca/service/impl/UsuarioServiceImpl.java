package com.biblioteca.app.biblioteca.service.impl;


import com.biblioteca.app.biblioteca.model.Usuario;
import com.biblioteca.app.biblioteca.repository.UsuarioRepository;
import com.biblioteca.app.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.getReferenceById(id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario save(Usuario prestamo){
        return usuarioRepository.save(prestamo);
    }

    @Override
    public void deleteUsuario(Usuario prestamo) {
        usuarioRepository.delete(prestamo);
    }

    @Override
    public Usuario findByNombre(String nombre){
        return usuarioRepository.findByNombre(nombre);
    }
}
