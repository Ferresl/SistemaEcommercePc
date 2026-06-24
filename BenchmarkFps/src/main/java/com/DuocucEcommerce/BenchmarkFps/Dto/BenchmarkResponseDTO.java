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
@Schema(description = "Respuesta entregada por la API para benchmark.")
public class BenchmarkResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del procesador seleccionado.", example = "1")
    private Integer cpuId;

    @Schema(description = "Identificador de la tarjeta grafica seleccionada.", example = "1")
    private Integer gpuId;

    @Schema(description = "Identificador del videojuego usado en la prueba.", example = "1")
    private Integer videojuegoId;

    @Schema(description = "Identificador de la resolucion usada en la prueba.", example = "1")
    private Integer resolucionId;

    @Schema(description = "FPS promedio registrado en el benchmark.", example = "95")
    private Double fpsPromedio;

}
