package com.example.zy6r_4E_Alexis_Riveraa.service;


import com.example.zy6r_4E_Alexis_Riveraa.controller.Dto.RequestDevolucionDto;
import com.example.zy6r_4E_Alexis_Riveraa.controller.Dto.RequestRegistrarPrestamoDto;
import com.example.zy6r_4E_Alexis_Riveraa.exception.CustomBadRequestException;
import com.example.zy6r_4E_Alexis_Riveraa.exception.RecursoNoEncontradoException;
import com.example.zy6r_4E_Alexis_Riveraa.model.EstadoPrestamo;
import com.example.zy6r_4E_Alexis_Riveraa.model.Prestamo;
import com.example.zy6r_4E_Alexis_Riveraa.model.Videojuego;
import com.example.zy6r_4E_Alexis_Riveraa.repository.PrestamoRepository;
import com.example.zy6r_4E_Alexis_Riveraa.repository.VideojuegoRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Setter
@Getter
public class PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final VideojuegoRepository videojuegoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository, VideojuegoRepository videojuegoRepository) {
        this.prestamoRepository = prestamoRepository;
        this.videojuegoRepository = videojuegoRepository;
    }

    @Transactional
    public Prestamo registrarPrestamo(RequestRegistrarPrestamoDto payload) {
        Videojuego videojuego = buscarVideojuego(payload.getVideojuegoId());

        if (!videojuego.isDisponible()) {
            throw new CustomBadRequestException("El videojuego no esta disponible");
        }

        Prestamo prestamo = new Prestamo();
        prestamo.setNombreSolicitante(payload.getNombreSolicitante());
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(null);
        prestamo.setEstado(EstadoPrestamo.ACTIVO);
        prestamo.setVideojuego(videojuego);

        videojuego.setDisponible(false);
        videojuegoRepository.save(videojuego);

        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public Prestamo registrarDevolucion(RequestDevolucionDto payload) {
        Videojuego videojuego = buscarVideojuego(payload.getVideojuegoId());

        Prestamo prestamo = prestamoRepository.findFirstByVideojuegoIdOrderByIdDesc(videojuego.getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("El videojuego no tiene prestamos registrados"));

        if (prestamo.getEstado() != EstadoPrestamo.ACTIVO) {
            throw new CustomBadRequestException("Solo se pueden devolver prestamos ACTIVO. Estado actual: " + prestamo.getEstado());
        }

        prestamo.setEstado(EstadoPrestamo.DEVUELTO);
        prestamo.setFechaDevolucion(LocalDate.now());

        videojuego.setDisponible(true);
        videojuegoRepository.save(videojuego);

        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public Prestamo cancelarPrestamo(Long prestamoId) {
        Prestamo prestamo = prestamoRepository.findById(prestamoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("El prestamo con id " + prestamoId + " no existe"));

        if (prestamo.getEstado() != EstadoPrestamo.ACTIVO) {
            throw new CustomBadRequestException("Solo se pueden cancelar prestamos ACTIVO. Estado actual: " + prestamo.getEstado());
        }

        prestamo.setEstado(EstadoPrestamo.CANCELADO);

        Videojuego videojuego = prestamo.getVideojuego();
        videojuego.setDisponible(true);
        videojuegoRepository.save(videojuego);

        return prestamoRepository.save(prestamo);
    }

    @Transactional
    public List<Prestamo> cancelarPrestamosActivos(Long videojuegoId) {
        Videojuego videojuego = buscarVideojuego(videojuegoId);

        List<Prestamo> activos = prestamoRepository.findByVideojuegoIdAndEstado(videojuego.getId(), EstadoPrestamo.ACTIVO);
        for (Prestamo prestamo : activos) {
            prestamo.setEstado(EstadoPrestamo.CANCELADO);
        }

        videojuego.setDisponible(true);
        videojuegoRepository.save(videojuego);

        return prestamoRepository.saveAll(activos);
    }


    private Videojuego buscarVideojuego(Long id) {
        return videojuegoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El videojuego con id " + id + " no existe"));
    }
}