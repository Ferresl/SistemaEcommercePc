package com.DuocucEcommerce.ConfiguracionPc.Client;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
@Data
@Schema(description = "Respuesta entregada por la API para cpuresponse.")
public class CPUResponseDTO { private Integer productoId; private Integer tdpWatts; }

