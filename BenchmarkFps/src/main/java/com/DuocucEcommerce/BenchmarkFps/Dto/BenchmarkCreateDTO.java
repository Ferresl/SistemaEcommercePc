package com.DuocucEcommerce.BenchmarkFps.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos necesarios para crear benchmark.")
public class BenchmarkCreateDTO {

    @NotNull(message = "El campo cpuId es obligatorio")
    @Schema(description = "Identificador del procesador seleccionado.", example = "1")
    private Integer cpuId;

    @NotNull(message = "El campo gpuId es obligatorio")
    @Schema(description = "Identificador de la tarjeta grafica seleccionada.", example = "1")
    private Integer gpuId;

    @NotNull(message = "El campo videojuegoId es obligatorio")
    @Schema(description = "Identificador del videojuego usado en la prueba.", example = "1")
    private Integer videojuegoId;

    @NotNull(message = "El campo resolucionId es obligatorio")
    @Schema(description = "Identificador de la resolucion usada en la prueba.", example = "1")
    private Integer resolucionId;

    @NotNull(message = "El FPS es obligatorio")
    @Positive(message = "El FPS debe ser mayor a 0")
    @Schema(description = "FPS promedio registrado en el benchmark.", example = "95")
    private Double fpsPromedio;

}
