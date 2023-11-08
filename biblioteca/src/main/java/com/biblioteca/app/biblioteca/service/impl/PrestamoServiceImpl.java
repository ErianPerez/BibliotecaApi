package com.biblioteca.app.biblioteca.service.impl;


import com.biblioteca.app.biblioteca.model.Prestamo;
import com.biblioteca.app.biblioteca.repository.PrestamoRepository;
import com.biblioteca.app.biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {
    @Autowired
    private PrestamoRepository prestamoRepository;

    @Override
    public Prestamo findById(Long id) {
        return prestamoRepository.getReferenceById(id);
    }

    @Override
    public List<Prestamo> findAll() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo save(Prestamo prestamo){
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void deletePrestamo(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);
    }

    @Override
    public Prestamo findByDate(Date fecha){
        return prestamoRepository.findByFechaPrestamo(fecha);
    }
    @Override
    public Prestamo findByUsuario(Long usuario){
        return prestamoRepository.findByUsuarioId(usuario);
    }
    @Override
    public List<Prestamo> findByMonthAndYear(int month, int year) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        Date start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endDate.atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());

        return prestamoRepository.findByFechaPrestamoBetween(start, end);
    }
}
