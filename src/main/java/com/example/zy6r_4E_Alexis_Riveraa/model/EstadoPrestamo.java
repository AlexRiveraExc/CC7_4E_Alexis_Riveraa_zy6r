package com.example.zy6r_4E_Alexis_Riveraa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class EstadoPrestamo {
    public static final EstadoPrestamo DEVUELTO = null;
    public static EstadoPrestamo ACTIVO;
    public static EstadoPrestamo CANCELADO;
    @Id
    private Long id;



    //TODO [Reverse Engineering] generate columns from DB
}