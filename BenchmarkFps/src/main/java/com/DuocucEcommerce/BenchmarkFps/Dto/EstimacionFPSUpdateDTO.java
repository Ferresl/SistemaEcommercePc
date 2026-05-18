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
public class EstimacionFPSUpdateDTO {

    @NotNull(message = "El campo configuracionId es obligatorio")
    private Integer configuracionId;

    @NotNull(message = "El FPS es obligatorio")
    @Positive(message = "El FPS debe ser mayor a 0")
    private Double fpsEstimado;

}
