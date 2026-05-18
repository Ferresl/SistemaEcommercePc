package com.DuocucEcommerce.BenchmarkFps.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenchmarkCreateDTO {

    @NotNull(message = "El campo cpuId es obligatorio")
    private Integer cpuId;

    @NotNull(message = "El campo gpuId es obligatorio")
    private Integer gpuId;

    @NotNull(message = "El campo videojuegoId es obligatorio")
    private Integer videojuegoId;

    @NotNull(message = "El campo resolucionId es obligatorio")
    private Integer resolucionId;

    @NotNull(message = "El FPS es obligatorio")
    @Positive(message = "El FPS debe ser mayor a 0")
    private Double fpsPromedio;

}
