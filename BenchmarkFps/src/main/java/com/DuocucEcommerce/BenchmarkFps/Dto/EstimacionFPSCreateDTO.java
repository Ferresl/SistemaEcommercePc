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
@Schema(description = "Datos necesarios para crear estimacion de FPS.")
public class EstimacionFPSCreateDTO {

    @NotNull(message = "El campo configuracionId es obligatorio")
    @Schema(description = "Identificador de la configuracion de PC.", example = "1")
    private Integer configuracionId;

    @NotNull(message = "El FPS es obligatorio")
    @Positive(message = "El FPS debe ser mayor a 0")
    @Schema(description = "FPS estimado para la configuracion indicada.", example = "95.5")
    private Double fpsEstimado;

}
