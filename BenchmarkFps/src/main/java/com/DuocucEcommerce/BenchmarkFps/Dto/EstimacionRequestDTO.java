package com.DuocucEcommerce.BenchmarkFps.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstimacionRequestDTO {
    @NotNull private Integer configuracionId;
    @NotNull private Integer cpuId;
    @NotNull private Integer gpuId;
    @NotNull private Integer videojuegoId;
    @NotNull private Integer resolucionId;
}
