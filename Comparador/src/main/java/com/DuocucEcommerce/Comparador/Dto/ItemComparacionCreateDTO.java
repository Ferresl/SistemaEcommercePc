package com.DuocucEcommerce.Comparador.Dto;

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
@Schema(description = "Datos necesarios para crear item de comparacion.")
public class ItemComparacionCreateDTO {

    @NotNull(message = "El campo comparacionId es obligatorio")
    @Schema(description = "Identificador asociado a comparacionId.", example = "1")
    private Integer comparacionId;

    @NotNull(message = "El campo productoId es obligatorio")
    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

}
