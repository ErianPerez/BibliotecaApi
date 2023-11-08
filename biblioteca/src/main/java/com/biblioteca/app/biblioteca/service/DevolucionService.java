package com.biblioteca.app.biblioteca.service;

import com.biblioteca.app.biblioteca.model.Devolucion;
import com.biblioteca.app.biblioteca.model.Prestamo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface DevolucionService {
    Devolucion findById(Long id);

    List<Devolucion> findAll();

    Devolucion save(Devolucion devolucion);

    void deleteDevolucion(Devolucion devolucion);

    Devolucion findByDate(Date fecha);
}
