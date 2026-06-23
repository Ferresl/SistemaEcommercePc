package com.DuocucEcommerce.ConfiguracionPc.Client;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
@Data
@Schema(description = "Respuesta entregada por la API para compatibilidad.")
public class CompatibilidadResponseDTO { private Integer resultadoId; private Integer configuracionId; private String estado; private String mensaje; }

