package com.DuocucEcommerce.Compatibilidad.Client;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
@Data
@Schema(description = "Respuesta entregada por la API para fuente de poder.")
public class FuentePoderResponseDTO { private Integer productoId; private Integer potenciaWatts; }

