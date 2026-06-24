package com.DuocucEcommerce.Carrito.Dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para carrito.")
public class CarritoResponseDTO {
    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;
    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;
    @Schema(description = "Subtotal calculado antes del total final.", example = "399990")
    private BigDecimal subtotal;
    @Schema(description = "Monto total calculado.", example = "799990")
    private BigDecimal total;
    @Schema(description = "Items incluidos en el carrito.", example = "[]")
    private List<ItemCarritoResponseDTO> items;
}

