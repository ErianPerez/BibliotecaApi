package com.biblioteca.app.biblioteca.controller;

import com.biblioteca.app.biblioteca.model.Libro;
import com.biblioteca.app.biblioteca.model.LibroPopular;
import com.biblioteca.app.biblioteca.model.Prestamo;
import com.biblioteca.app.biblioteca.service.LibroService;
import com.biblioteca.app.biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping("/")
    public List<Libro> listarLibros(){
        return libroService.findAll();
    }

    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable Long id){
        return libroService.findById(id);
    }

    @GetMapping("/{nombre}")
    public Libro findLibroByNombre(@PathVariable String nombre){
        return libroService.findByNombre(nombre);
    }
    @GetMapping("/{autor}")
    public Libro findLibroByAutor(@PathVariable String autor){
        return libroService.findByAutor(autor);
    }
    @GetMapping("/{categoria}")
    public Libro findLibroByCategoria(@PathVariable String categoria){ return libroService.findByCategoria(categoria);}

    @PostMapping
    public Libro crearLibro(@RequestBody Libro libro){
        return libroService.save(libro);
    }

    @PutMapping("/{id}")
    public Libro actualizarLibro(@RequestBody Libro libro){
        return libroService.save(libro);
    }

    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id){
        Libro libro = new Libro();
        libro.setId(id);
        libroService.deleteLibro(libro);
    }

    @GetMapping("/populares/{mes}-{anio}")
    public List<Libro> findLibrosPopularesDelMes(@PathVariable int mes, int anio){ return libroService.generarInformeMensualLibrosPopulares(mes, anio);}


}
