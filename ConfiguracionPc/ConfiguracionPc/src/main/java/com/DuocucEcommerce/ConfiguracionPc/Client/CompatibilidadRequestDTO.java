package com.DuocucEcommerce.ConfiguracionPc.Client;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class CompatibilidadRequestDTO { private Integer configuracionId; private Integer cpuId; private Integer gpuId; private Integer ramId; private Integer placaMadreId; private Integer fuentePoderId; private Integer gabineteId; private Integer tdpTotal; }

