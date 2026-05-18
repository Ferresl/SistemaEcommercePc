package com.DuocucEcommerce.Carrito.Client;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDTO { private Integer id; private String nombre; private BigDecimal precio; private Integer categoriaId; }

