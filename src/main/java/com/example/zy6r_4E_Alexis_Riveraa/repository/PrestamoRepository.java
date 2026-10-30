package com.example.zy6r_4E_Alexis_Riveraa.repository;


import com.example.zy6r_4E_Alexis_Riveraa.model.EstadoPrestamo;
import com.example.zy6r_4E_Alexis_Riveraa.model.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
    public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

        Optional<Prestamo> findFirstByVideojuegoIdOrderByIdDesc(Long videojuegoId);

        List<Prestamo> findByVideojuegoIdAndEstado(Long videojuegoId, EstadoPrestamo estado);

        List<Prestamo> findByEstado(EstadoPrestamo estado);
    }

