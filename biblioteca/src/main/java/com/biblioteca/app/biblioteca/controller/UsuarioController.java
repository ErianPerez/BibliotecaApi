package com.biblioteca.app.biblioteca.controller;

import com.biblioteca.app.biblioteca.model.Libro;
import com.biblioteca.app.biblioteca.model.Usuario;
import com.biblioteca.app.biblioteca.service.LibroService;
import com.biblioteca.app.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public List<Usuario> listarUsuarios(){
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getUsuarioById(@PathVariable Long id){
        return usuarioService.findById(id);
    }

    @GetMapping("/{nombre}")
    public Usuario findUsuarioByNombre(@PathVariable String nombre){
        return usuarioService.findByNombre(nombre);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuarioService.deleteUsuario(usuario);
    }
}
