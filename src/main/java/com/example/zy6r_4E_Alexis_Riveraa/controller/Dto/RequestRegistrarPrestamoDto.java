package com.example.zy6r_4E_Alexis_Riveraa.controller.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestRegistrarPrestamoDto {

    @NotNull(message = "El id del videojuego es obligatorio")
    private Long videojuegoId;

    @NotBlank(message = "El nombre del solicitante es obligatorio")
    private String nombreSolicitante;
}
