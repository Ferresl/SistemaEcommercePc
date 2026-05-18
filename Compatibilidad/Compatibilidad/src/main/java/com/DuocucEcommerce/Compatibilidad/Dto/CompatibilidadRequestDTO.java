package com.DuocucEcommerce.Compatibilidad.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompatibilidadRequestDTO {
    @NotNull private Integer configuracionId;
    @NotNull private Integer cpuId;
    @NotNull private Integer gpuId;
    @NotNull private Integer ramId;
    @NotNull private Integer placaMadreId;
    @NotNull private Integer fuentePoderId;
    @NotNull private Integer gabineteId;
    @NotNull private Integer tdpTotal;
}