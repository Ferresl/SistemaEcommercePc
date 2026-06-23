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
public class ConflictoCompatibilidadCreateDTO {

    @NotNull(message = "El campo resultadoId es obligatorio")
    private Integer resultadoId;

    @NotNull(message = "El campo productoAId es obligatorio")
    private Integer productoAId;

    @NotNull(message = "El campo productoBId es obligatorio")
    private Integer productoBId;

    @NotBlank(message = "El campo motivo es obligatorio")
    private String motivo;

}