package com.DuocucEcommerce.BenchmarkFps.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de entrada para estimacion de FPS.")
public class EstimacionRequestDTO {
    @Schema(description = "Identificador de la configuracion de PC.", example = "1")
    @NotNull private Integer configuracionId;
    @Schema(description = "Identificador del procesador seleccionado.", example = "1")
    @NotNull private Integer cpuId;
    @Schema(description = "Identificador de la tarjeta grafica seleccionada.", example = "1")
    @NotNull private Integer gpuId;
    @Schema(description = "Identificador del videojuego usado en la estimacion.", example = "1")
    @NotNull private Integer videojuegoId;
    @Schema(description = "Identificador de la resolucion usada en la prueba.", example = "1")
    @NotNull private Integer resolucionId;
}
