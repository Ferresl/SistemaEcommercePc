package com.DuocucEcommerce.Carrito.Dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCarritoUpdateDTO {

    @NotNull(message = "El campo carritoId es obligatorio")
    private Integer carritoId;

    @NotNull(message = "El campo productoId es obligatorio")
    private Integer productoId;

    private Integer configuracionId;

    @NotNull(message = "El campo cantidad es obligatorio")
    @Min(value = 1, message = "El campo cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "El campo precioUnitario es obligatorio")
    @Positive(message = "El campo precioUnitario debe ser mayor a 0")
    private BigDecimal precioUnitario;

    @NotNull(message = "El campo subtotal es obligatorio")
    @Positive(message = "El campo subtotal debe ser mayor a 0")
    private BigDecimal subtotal;

}
