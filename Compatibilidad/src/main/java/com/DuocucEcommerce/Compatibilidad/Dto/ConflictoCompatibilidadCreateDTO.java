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
@Schema(description = "Datos necesarios para crear conflicto de compatibilidad.")
public class ConflictoCompatibilidadCreateDTO {

    @NotNull(message = "El campo resultadoId es obligatorio")
    @Schema(description = "Identificador del resultado de compatibilidad asociado.", example = "1")
    private Integer resultadoId;

    @NotNull(message = "El campo productoAId es obligatorio")
    @Schema(description = "Identificador del primer producto involucrado en el conflicto.", example = "1")
    private Integer productoAId;

    @NotNull(message = "El campo productoBId es obligatorio")
    @Schema(description = "Identificador del segundo producto involucrado en el conflicto.", example = "2")
    private Integer productoBId;

    @NotBlank(message = "El campo motivo es obligatorio")
    @Schema(description = "Motivo del conflicto de compatibilidad.", example = "La fuente de poder no cubre el consumo estimado")
    private String motivo;

}
