package com.DuocucEcommerce.Comparador.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos usados por la API para agregar item comparacion.")
public class AgregarItemComparacionDTO {
    @NotNull(message = "El producto es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;
}
