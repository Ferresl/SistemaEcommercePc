package com.DuocucEcommerce.Inventario.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioUpdateDTO {

    @NotNull(message = "El campo productoId es obligatorio")
    private Integer productoId;

    @NotNull(message = "El campo stockDisponible es obligatorio")
    @Min(value = 0, message = "El campo stockDisponible no puede ser negativo")
    private Integer stockDisponible;

    @NotNull(message = "El campo stockReservado es obligatorio")
    @Min(value = 0, message = "El campo stockReservado no puede ser negativo")
    private Integer stockReservado;

    @NotNull(message = "El campo stockMinimo es obligatorio")
    @Min(value = 0, message = "El campo stockMinimo no puede ser negativo")
    private Integer stockMinimo;

}
