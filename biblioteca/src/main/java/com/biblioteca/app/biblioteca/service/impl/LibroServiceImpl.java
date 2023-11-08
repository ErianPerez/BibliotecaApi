package com.biblioteca.app.biblioteca.service.impl;
import com.biblioteca.app.biblioteca.model.Libro;
import com.biblioteca.app.biblioteca.model.LibroPopular;
import com.biblioteca.app.biblioteca.model.Prestamo;
import com.biblioteca.app.biblioteca.repository.LibroRepository;
import com.biblioteca.app.biblioteca.repository.PrestamoRepository;
import com.biblioteca.app.biblioteca.service.LibroService;
import com.biblioteca.app.biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;
    private PrestamoService prestamoService;

    @Override
    public Libro findById(Long id) {
        return libroRepository.getReferenceById(id);
    }

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Libro save(Libro libro){
        return libroRepository.save(libro);
    }

    @Override
    public void deleteLibro(Libro libro) {
        libroRepository.delete(libro);
    }

    @Override
    public Libro findByNombre(String nombre){
        return libroRepository.findByNombre(nombre);
    }
    @Override
    public Libro findByAutor(String autor){
        return libroRepository.findByAutor(autor);
    }
    @Override
    public Libro findByCategoria(String categoria){
        return libroRepository.findByCategoria(categoria);
    }

    public List<Libro> generarInformeMensualLibrosPopulares(int mes, int anio) {
        List<Prestamo> prestamos = prestamoService.findByMonthAndYear(mes, anio);

        Map<Libro, Integer> conteoLibros = new HashMap<>();

        for (Prestamo prestamo : prestamos) {
            Libro libroPrestado = prestamo.getLibro();

            conteoLibros.put(libroPrestado, conteoLibros.getOrDefault(libroPrestado, 0) + 1);
        }

        List<Libro> librosPopulares = new ArrayList<>();

        conteoLibros.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .forEach(entry -> {
                    LibroPopular libroPopular = new LibroPopular();
                    libroPopular.setLibro(entry.getKey());
                    libroPopular.setCantidadPrestamos(entry.getValue());
                    librosPopulares.add(libroPopular.getLibro());
                });

        return librosPopulares;
    }
}
