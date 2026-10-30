package com.example.zy6r_4E_Alexis_Riveraa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;



    @Entity
    @Table(name = "prestamos")
    @Getter
    @Setter
    @NoArgsConstructor

    public class Prestamo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nombreSolicitante;
        private LocalDate fechaPrestamo;
        private LocalDate fechaDevolucion;

        @Enumerated(EnumType.STRING)
         private EstadoPrestamo estado;

        @ManyToOne(optional = false)
        @JoinColumn(name = "videojuego_id", nullable = false)
        private Videojuego videojuego;
    }



