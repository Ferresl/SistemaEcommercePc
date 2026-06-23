package com.DuocucEcommerce.Inventario.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para inventario.")
public class InventarioResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @Schema(description = "Stock disponible para venta.", example = "15")
    private Integer stockDisponible;

    @Schema(description = "Valor numerico de stockReservado.", example = "1")
    private Integer stockReservado;

    @Schema(description = "Valor numerico de stockMinimo.", example = "1")
    private Integer stockMinimo;

}
