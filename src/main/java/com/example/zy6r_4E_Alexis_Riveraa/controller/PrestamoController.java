package com.example.zy6r_4E_Alexis_Riveraa.controller;



import com.example.zy6r_4E_Alexis_Riveraa.controller.Dto.RequestDevolucionDto;
import com.example.zy6r_4E_Alexis_Riveraa.controller.Dto.RequestRegistrarPrestamoDto;
import com.example.zy6r_4E_Alexis_Riveraa.model.Prestamo;
import com.example.zy6r_4E_Alexis_Riveraa.service.PrestamoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @PostMapping
    public ResponseEntity<Prestamo> registrarPrestamo(@RequestBody @Valid RequestRegistrarPrestamoDto payload) {
        return ResponseEntity.status(201).body(prestamoService.registrarPrestamo(payload));
    }

    @PutMapping("/devolucion")
    public ResponseEntity<Prestamo> registrarDevolucion(@RequestBody @Valid RequestDevolucionDto payload) {
        return ResponseEntity.ok(prestamoService.registrarDevolucion(payload));
    }

    @PutMapping("/{id}/cancelacion")
    public ResponseEntity<Prestamo> cancelarPrestamo(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.cancelarPrestamo(id));
    }

    @PutMapping("/videojuegos/{videojuegoId}/cancelacion")
    public ResponseEntity<List<Prestamo>> cancelarPrestamosActivos(@PathVariable Long videojuegoId) {
        return ResponseEntity.ok(prestamoService.cancelarPrestamosActivos(videojuegoId));
    }


}