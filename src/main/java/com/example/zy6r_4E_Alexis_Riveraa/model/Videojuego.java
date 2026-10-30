package com.example.zy6r_4E_Alexis_Riveraa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Entity
    @Table(name = "videojuegos")
    @Getter
    @Setter
    @NoArgsConstructor
    public class Videojuego {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String titulo;
        private String plataforma;
        private boolean disponible;
    }

