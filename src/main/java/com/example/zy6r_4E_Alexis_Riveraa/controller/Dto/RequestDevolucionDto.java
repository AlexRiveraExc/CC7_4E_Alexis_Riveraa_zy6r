package com.example.zy6r_4E_Alexis_Riveraa.controller.Dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestDevolucionDto {

    @NotNull(message = "El id del videojuego es obligatorio")
    private Long videojuegoId;
}

