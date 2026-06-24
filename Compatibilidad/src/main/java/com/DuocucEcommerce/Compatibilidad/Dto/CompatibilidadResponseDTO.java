package com.DuocucEcommerce.Compatibilidad.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para compatibilidad.")
public class CompatibilidadResponseDTO {
    @Schema(description = "Identificador del resultado de compatibilidad asociado.", example = "1")
    private Integer resultadoId;
    @Schema(description = "Identificador de la configuracion de PC.", example = "1")
    private Integer configuracionId;
    @Schema(description = "Estado actual de la compatibilidad o configuracion.", example = "COMPATIBLE")
    private String estado;
    @Schema(description = "Mensaje descriptivo del resultado de compatibilidad.", example = "La configuracion es compatible")
    private String mensaje;
    @Schema(description = "Lista de conflictos detectados en la configuracion.", example = "[]")
    private List<ConflictoCompatibilidadResponseDTO> conflictos;
    @Schema(description = "Lista de recomendaciones para resolver conflictos.", example = "[]")
    private List<RecomendacionComponenteResponseDTO> recomendaciones;
}
