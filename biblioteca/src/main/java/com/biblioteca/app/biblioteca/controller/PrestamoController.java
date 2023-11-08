package com.biblioteca.app.biblioteca.controller;

import com.biblioteca.app.biblioteca.model.Libro;
import com.biblioteca.app.biblioteca.model.Prestamo;
import com.biblioteca.app.biblioteca.model.Usuario;
import com.biblioteca.app.biblioteca.service.LibroService;
import com.biblioteca.app.biblioteca.service.PrestamoService;
import com.biblioteca.app.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;
    private LibroService libroService;
    private UsuarioService usuarioService;

    @GetMapping("/")
    public List<Prestamo> listarPrestamos(){
        return prestamoService.findAll();
    }

    @GetMapping("/{id}")
    public Prestamo getPrestamoById(@PathVariable Long id){
        return prestamoService.findById(id);
    }

    @GetMapping("/{usuario}")
    public Prestamo findPrestamoByUsuario(@PathVariable Long usuario){
        return prestamoService.findByUsuario(usuario);
    }

    @GetMapping("/{fecha}")
    public Prestamo findPrestamoByDate(@PathVariable Date fecha){
        return prestamoService.findByDate(fecha);
    }

    @PostMapping
    public Prestamo realizarPrestamo(@RequestBody Prestamo prestamo) {
        Libro libro = libroService.findById(prestamo.getLibro().getId());
        Usuario usuario = usuarioService.findById(prestamo.getUsuario().getId());

        Date fechaPrestamo = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaPrestamo);
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date fechaVencimiento = calendar.getTime();

        Prestamo newPrestamo = new Prestamo();
        newPrestamo.setLibro(libro);
        newPrestamo.setUsuario(usuario);
        newPrestamo.setFechaPrestamo(fechaPrestamo);
        newPrestamo.setFechaDevolucion(fechaVencimiento);

        // Guarda el préstamo en la base de datos
        return prestamoService.save(newPrestamo);
    }


    @PutMapping("/{id}")
    public Prestamo actualizarPrestamo(@RequestBody Prestamo prestamo){
        return prestamoService.save(prestamo);
    }

    @DeleteMapping("/{id}")
    public void eliminarPrestamo(@PathVariable Long id){
        Prestamo prestamo = new Prestamo();
        prestamo.setId(id);
        prestamoService.deletePrestamo(prestamo);
    }
}
