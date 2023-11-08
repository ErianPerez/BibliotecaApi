package com.biblioteca.app.biblioteca.service;

import com.biblioteca.app.biblioteca.model.Libro;
import com.biblioteca.app.biblioteca.model.Prestamo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
@Service
public interface PrestamoService {
    Prestamo findById(Long id);

    List<Prestamo> findAll();

    Prestamo save(Prestamo prestamo);

    void deletePrestamo(Prestamo libro);

    Prestamo findByDate(Date fecha);
    Prestamo findByUsuario(Long usuario);

    List<Prestamo> findByMonthAndYear(int month, int year);
}
