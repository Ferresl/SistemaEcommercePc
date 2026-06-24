package com.DuocucEcommerce.Compatibilidad.Client;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
@Data
@Schema(description = "Respuesta entregada por la API para gabinete.")
public class GabineteResponseDTO { private Integer productoId; private String formatoSoportado; private Integer largoMaxGpuMm; }

