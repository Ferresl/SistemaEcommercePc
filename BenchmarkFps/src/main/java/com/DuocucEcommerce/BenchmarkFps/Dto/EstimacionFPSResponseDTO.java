package com.DuocucEcommerce.BenchmarkFps.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para estimacion de FPS.")
public class EstimacionFPSResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador de la configuracion de PC.", example = "1")
    private Integer configuracionId;

    @Schema(description = "FPS estimado para la configuracion indicada.", example = "95.5")
    private Double fpsEstimado;

}
