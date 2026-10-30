package com.example.zy6r_4E_Alexis_Riveraa.controller;




import com.example.zy6r_4E_Alexis_Riveraa.controller.Dto.RequestRegistrarVideojuegoDto;
import com.example.zy6r_4E_Alexis_Riveraa.model.Videojuego;
import com.example.zy6r_4E_Alexis_Riveraa.service.VideojuegoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/videojuegos")
public class VideojuegoController {

    private final VideojuegoService videojuegoService;

    public VideojuegoController(VideojuegoService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }

    @PostMapping
    public ResponseEntity<Videojuego> registrarVideojuego(@RequestBody @Valid RequestRegistrarVideojuegoDto payload) {
        return ResponseEntity.status(201).body(videojuegoService.registrarVideojuego(payload));
    }

    @GetMapping
    public ResponseEntity<List<Videojuego>> listarTodos() {
        return ResponseEntity.ok(videojuegoService.listarTodos());
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Videojuego>> listarDisponibles() {
        return ResponseEntity.ok(videojuegoService.listarDisponibles());
    }
}