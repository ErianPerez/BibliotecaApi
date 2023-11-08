package com.biblioteca.app.biblioteca.controller;

import com.biblioteca.app.biblioteca.model.Devolucion;
import com.biblioteca.app.biblioteca.model.Multa;
import com.biblioteca.app.biblioteca.model.Prestamo;
import com.biblioteca.app.biblioteca.service.DevolucionService;
import com.biblioteca.app.biblioteca.service.LibroService;
import com.biblioteca.app.biblioteca.service.PrestamoService;
import com.biblioteca.app.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/devoluciones")
public class DevolucionController {
    private static final long TASA_MULTA_POR_DIA = 5;
    @Autowired
    private DevolucionService devolucionService;
    private PrestamoService prestamoService;
    private LibroService libroService;
    private UsuarioService usuarioService;

    @GetMapping("/")
    public List<Devolucion> listarDevoluciones(){
        return devolucionService.findAll();
    }

    @GetMapping("/{id}")
    public Devolucion getDevolucionById(@PathVariable Long id){
        return devolucionService.findById(id);
    }

    @GetMapping("/{fecha}")
    public Devolucion findDevolucionByDate(@PathVariable Date fecha){
        return devolucionService.findByDate(fecha);
    }

    @PostMapping
    public Devolucion realizarDevolucion(@RequestBody Devolucion devolucion) {
        Long prestamoId = devolucion.getPrestamo().getId();
        Date fechaDevolucion = devolucion.getFechaDevolucion();

        Prestamo prestamo = prestamoService.findById(prestamoId);

        Date fechaVencimiento = prestamo.getFechaDevolucion();
        Multa multa = null;
        if (fechaDevolucion.after(fechaVencimiento)) {
            long diasAtraso = (fechaDevolucion.getTime() - fechaVencimiento.getTime()) / (24 * 60 * 60 * 1000);

            double montoMulta = diasAtraso * TASA_MULTA_POR_DIA;

            multa = new Multa();
            multa.setValor(montoMulta);
        }

        Devolucion newDevolucion = new Devolucion();
        newDevolucion.setPrestamo(prestamo);
        newDevolucion.setFechaDevolucion(fechaDevolucion);
        newDevolucion.setMulta(multa);

        return devolucionService.save(devolucion);
    }

    @PutMapping("/{id}")
    public Devolucion actualizarDevolucion(@RequestBody Devolucion devolucion){
        return devolucionService.save(devolucion);
    }

    @DeleteMapping("/{id}")
    public void eliminarDevolucion(@PathVariable Long id) {
        Devolucion devolucion = new Devolucion();
        devolucion.setId(id);
        devolucionService.deleteDevolucion(devolucion);

    }
}
