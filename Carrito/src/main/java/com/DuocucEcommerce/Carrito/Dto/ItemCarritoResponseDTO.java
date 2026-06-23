package com.DuocucEcommerce.Carrito.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCarritoResponseDTO {

    private Integer id;

    private Integer carritoId;

    private Integer productoId;

    private Integer configuracionId;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subtotal;

}
