package com.DuocucEcommerce.Compatibilidad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para conflicto de compatibilidad.")
public class ConflictoCompatibilidadResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del resultado de compatibilidad asociado.", example = "1")
    private Integer resultadoId;

    @Schema(description = "Identificador del primer producto involucrado en el conflicto.", example = "1")
    private Integer productoAId;

    @Schema(description = "Identificador del segundo producto involucrado en el conflicto.", example = "2")
    private Integer productoBId;

    @Schema(description = "Motivo del conflicto de compatibilidad.", example = "La fuente de poder no cubre el consumo estimado")
    private String motivo;

}
