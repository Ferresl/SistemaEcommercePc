package com.DuocucEcommerce.BenchmarkFps.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenchmarkResponseDTO {

    private Integer id;

    private Integer cpuId;

    private Integer gpuId;

    private Integer videojuegoId;

    private Integer resolucionId;

    private Double fpsPromedio;

}
