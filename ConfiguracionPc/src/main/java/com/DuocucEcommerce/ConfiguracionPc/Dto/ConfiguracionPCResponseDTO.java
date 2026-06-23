package com.DuocucEcommerce.ConfiguracionPc.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionPCResponseDTO {

    private Integer id;

    private Integer usuarioId;

    private String nombre;

    private Integer cpuId;

    private Integer gpuId;

    private Integer ramId;

    private Integer placaMadreId;

    private Integer almacenamientoId;

    private Integer fuentePoderId;

    private Integer gabineteId;

    private BigDecimal precioTotal;

    private Integer tdpTotal;

    private String estado;

}
