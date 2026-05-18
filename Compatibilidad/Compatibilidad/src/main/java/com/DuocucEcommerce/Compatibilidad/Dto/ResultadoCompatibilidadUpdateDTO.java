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
public class ResultadoCompatibilidadUpdateDTO {

    @NotNull(message = "El campo configuracionId es obligatorio")
    private Integer configuracionId;

    @NotBlank(message = "El campo estado es obligatorio")
    private String estado;

    @NotBlank(message = "El campo mensaje es obligatorio")
    private String mensaje;

}