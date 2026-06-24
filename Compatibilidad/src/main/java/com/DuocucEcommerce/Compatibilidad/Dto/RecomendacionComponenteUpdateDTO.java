package com.DuocucEcommerce.Compatibilidad.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos permitidos para actualizar recomendacion de componente.")
public class RecomendacionComponenteUpdateDTO {

    @NotNull(message = "El campo conflictoId es obligatorio")
    @Schema(description = "Identificador del conflicto de compatibilidad asociado.", example = "1")
    private Integer conflictoId;

    @NotNull(message = "El campo productoRecomendadoId es obligatorio")
    @Schema(description = "Identificador del producto recomendado como alternativa.", example = "3")
    private Integer productoRecomendadoId;

    @NotBlank(message = "El campo motivo es obligatorio")
    @Schema(description = "Motivo de la recomendacion del componente.", example = "Mejora la compatibilidad de la configuracion")
    private String motivo;

}
