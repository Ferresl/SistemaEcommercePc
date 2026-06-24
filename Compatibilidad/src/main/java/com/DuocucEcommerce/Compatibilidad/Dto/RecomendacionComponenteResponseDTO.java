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
@Schema(description = "Respuesta entregada por la API para recomendacion de componente.")
public class RecomendacionComponenteResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del conflicto de compatibilidad asociado.", example = "1")
    private Integer conflictoId;

    @Schema(description = "Identificador del producto recomendado como alternativa.", example = "3")
    private Integer productoRecomendadoId;

    @Schema(description = "Motivo de la recomendacion del componente.", example = "Mejora la compatibilidad de la configuracion")
    private String motivo;

}
