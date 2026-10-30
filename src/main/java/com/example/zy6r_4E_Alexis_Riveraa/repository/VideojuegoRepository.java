package com.example.zy6r_4E_Alexis_Riveraa.repository;

import com.example.zy6r_4E_Alexis_Riveraa.model.Videojuego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideojuegoRepository extends JpaRepository<Videojuego, Long> {

    List<Videojuego> findByDisponibleTrue();
}