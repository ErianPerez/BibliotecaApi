package com.biblioteca.app.biblioteca.service;

import com.biblioteca.app.biblioteca.model.Libro;
import com.biblioteca.app.biblioteca.model.LibroPopular;
import com.biblioteca.app.biblioteca.model.Prestamo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LibroService {

    Libro findById(Long id);

    List<Libro> findAll();

    Libro save(Libro libro);

    void deleteLibro(Libro libro);

    Libro findByNombre(String nombre);
    Libro findByAutor(String autor);
    Libro findByCategoria(String categoria);
    List<Libro> generarInformeMensualLibrosPopulares(int mes, int anio);
}
