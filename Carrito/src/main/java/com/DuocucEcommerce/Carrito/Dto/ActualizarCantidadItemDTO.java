package com.DuocucEcommerce.Carrito.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos usados por la API para actualizar cantidad item.")
public class ActualizarCantidadItemDTO {
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Schema(description = "Cantidad de unidades solicitadas.", example = "2")
    private Integer cantidad;
}
