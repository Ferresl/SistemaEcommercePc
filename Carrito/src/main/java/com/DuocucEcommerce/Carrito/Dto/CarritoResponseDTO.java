package com.DuocucEcommerce.Carrito.Dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoResponseDTO {
    private Integer id;
    private Integer usuarioId;
    private BigDecimal subtotal;
    private BigDecimal total;
    private List<ItemCarritoResponseDTO> items;
}

