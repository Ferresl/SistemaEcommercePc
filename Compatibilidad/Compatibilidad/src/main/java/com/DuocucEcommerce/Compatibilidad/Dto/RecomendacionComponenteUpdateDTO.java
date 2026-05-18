package com.DuocucEcommerce.Compatibilidad.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecomendacionComponenteUpdateDTO {

    @NotNull(message = "El campo conflictoId es obligatorio")
    private Integer conflictoId;

    @NotNull(message = "El campo productoRecomendadoId es obligatorio")
    private Integer productoRecomendadoId;

    @NotBlank(message = "El campo motivo es obligatorio")
    private String motivo;

}