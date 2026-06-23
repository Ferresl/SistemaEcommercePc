package com.DuocucEcommerce.ConfiguracionPc.Client;

import lombok.Builder;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
@Data
@Builder
@Schema(description = "Datos de entrada para compatibilidad.")
public class CompatibilidadRequestDTO { private Integer configuracionId; private Integer cpuId; private Integer gpuId; private Integer ramId; private Integer placaMadreId; private Integer fuentePoderId; private Integer gabineteId; private Integer tdpTotal; }

