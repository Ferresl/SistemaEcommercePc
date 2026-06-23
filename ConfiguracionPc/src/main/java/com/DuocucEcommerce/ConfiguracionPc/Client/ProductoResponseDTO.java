package com.DuocucEcommerce.ConfiguracionPc.Client;

import java.math.BigDecimal;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
@Data
@Schema(description = "Respuesta entregada por la API para producto.")
public class ProductoResponseDTO { private Integer id; private BigDecimal precio; }

