package com.DuocucEcommerce.Carrito.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para item del carrito.")
public class ItemCarritoResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del carrito asociado.", example = "1")
    private Integer carritoId;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Identificador de la configuracion de PC.", example = "1")
    private Integer configuracionId;

    @Schema(description = "Cantidad de unidades solicitadas.", example = "2")
    private Integer cantidad;

    @Schema(description = "Precio unitario del producto al momento del pedido.", example = "249990")
    private BigDecimal precioUnitario;

    @Schema(description = "Subtotal calculado antes del total final.", example = "399990")
    private BigDecimal subtotal;

}
