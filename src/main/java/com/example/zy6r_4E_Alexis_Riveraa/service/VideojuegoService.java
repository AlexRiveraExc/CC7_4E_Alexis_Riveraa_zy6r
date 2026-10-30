package com.example.zy6r_4E_Alexis_Riveraa.service;


import com.example.zy6r_4E_Alexis_Riveraa.controller.Dto.RequestRegistrarVideojuegoDto;
import com.example.zy6r_4E_Alexis_Riveraa.model.Videojuego;
import com.example.zy6r_4E_Alexis_Riveraa.repository.VideojuegoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideojuegoService {

    private final VideojuegoRepository videojuegoRepository;

    public VideojuegoService(VideojuegoRepository videojuegoRepository) {
        this.videojuegoRepository = videojuegoRepository;
    }

    public Videojuego registrarVideojuego(RequestRegistrarVideojuegoDto payload) {
        Videojuego videojuego = new Videojuego();
        videojuego.setTitulo(payload.getTitulo());
        videojuego.setPlataforma(payload.getPlataforma());
        videojuego.setDisponible(true);
        return videojuegoRepository.save(videojuego);
    }

    public List<Videojuego> listarDisponibles() {
        return videojuegoRepository.findByDisponibleTrue();
    }

    public List<Videojuego> listarTodos() {
        return videojuegoRepository.findAll();
    }
}