package com.DuocucEcommerce.Compatibilidad.Client;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
@Data
@Schema(description = "Respuesta entregada por la API para RAM.")
public class RAMResponseDTO { private Integer productoId; private String tipoMemoria; }
