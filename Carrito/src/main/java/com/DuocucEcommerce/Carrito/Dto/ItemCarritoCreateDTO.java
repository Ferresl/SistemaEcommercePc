package com.DuocucEcommerce.Carrito.Dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos necesarios para crear item del carrito.")
public class ItemCarritoCreateDTO {

    @NotNull(message = "El campo carritoId es obligatorio")
    @Schema(description = "Identificador del carrito asociado.", example = "1")
    private Integer carritoId;

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Identificador de la configuracion de PC.", example = "1")
    private Integer configuracionId;

    @NotNull(message = "El campo cantidad es obligatorio")
    @Min(value = 1, message = "El campo cantidad debe ser mayor a 0")
    @Schema(description = "Cantidad de unidades solicitadas.", example = "2")
    private Integer cantidad;

    @NotNull(message = "El campo precioUnitario es obligatorio")
    @Positive(message = "El campo precioUnitario debe ser mayor a 0")
    @Schema(description = "Precio unitario del producto al momento del pedido.", example = "249990")
    private BigDecimal precioUnitario;

    @NotNull(message = "El campo subtotal es obligatorio")
    @Positive(message = "El campo subtotal debe ser mayor a 0")
    @Schema(description = "Subtotal calculado antes del total final.", example = "399990")
    private BigDecimal subtotal;

}
