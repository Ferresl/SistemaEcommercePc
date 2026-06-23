package com.DuocucEcommerce.Inventario.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos permitidos para actualizar inventario.")
public class InventarioUpdateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

    @NotNull(message = "El campo stockDisponible es obligatorio")
    @Min(value = 0, message = "El campo stockDisponible no puede ser negativo")
    @Schema(description = "Stock disponible para venta.", example = "15")
    private Integer stockDisponible;

    @NotNull(message = "El campo stockReservado es obligatorio")
    @Min(value = 0, message = "El campo stockReservado no puede ser negativo")
    @Schema(description = "Valor numerico de stockReservado.", example = "1")
    private Integer stockReservado;

    @NotNull(message = "El campo stockMinimo es obligatorio")
    @Min(value = 0, message = "El campo stockMinimo no puede ser negativo")
    @Schema(description = "Valor numerico de stockMinimo.", example = "1")
    private Integer stockMinimo;

}
