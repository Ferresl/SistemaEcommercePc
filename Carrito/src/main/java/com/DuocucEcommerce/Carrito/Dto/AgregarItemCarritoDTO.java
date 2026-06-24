package com.DuocucEcommerce.Carrito.Dto;

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
@Schema(description = "Datos usados por la API para agregar item carrito.")
public class AgregarItemCarritoDTO {
    @NotNull(message = "El producto es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;
    @Schema(description = "Identificador de la configuracion de PC.", example = "1")
    private Integer configuracionId;
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Schema(description = "Cantidad de unidades solicitadas.", example = "2")
    private Integer cantidad;
}
