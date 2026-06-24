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
@Schema(description = "Respuesta entregada por la API para resultado de compatibilidad.")
public class ResultadoCompatibilidadResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador de la configuracion de PC.", example = "1")
    private Integer configuracionId;

    @Schema(description = "Estado actual de la compatibilidad o configuracion.", example = "COMPATIBLE")
    private String estado;

    @Schema(description = "Mensaje descriptivo del resultado de compatibilidad.", example = "La configuracion es compatible")
    private String mensaje;

}
