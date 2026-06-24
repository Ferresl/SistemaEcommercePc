package com.DuocucEcommerce.Carrito.Client;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta entregada por la API para producto.")
public class ProductoResponseDTO { private Integer id; private String nombre; private BigDecimal precio; private Integer categoriaId; }

