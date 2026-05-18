package com.DuocucEcommerce.ConfiguracionPc.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionPCUpdateDTO {
    @NotNull private Integer usuarioId;
    @NotBlank private String nombre;
    @NotNull private Integer cpuId;
    @NotNull private Integer gpuId;
    @NotNull private Integer ramId;
    @NotNull private Integer placaMadreId;
    @NotNull private Integer almacenamientoId;
    @NotNull private Integer fuentePoderId;
    @NotNull private Integer gabineteId;
    private String estado;
}
