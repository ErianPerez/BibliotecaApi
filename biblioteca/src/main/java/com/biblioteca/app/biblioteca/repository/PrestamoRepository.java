package com.biblioteca.app.biblioteca.repository;

import com.biblioteca.app.biblioteca.model.Libro;
import com.biblioteca.app.biblioteca.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    Prestamo findByFechaPrestamo(Date fecha);
    Prestamo findByUsuarioId(Long usuario);
    List<Prestamo> findByFechaPrestamoBetween(Date start, Date end);
}
