package com.DuocucEcommerce.Comparador.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para item de comparacion.")
public class ItemComparacionResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador asociado a comparacionId.", example = "1")
    private Integer comparacionId;

    @Schema(description = "Identificador del producto asociado.", example = "1")
    private Integer productoId;

}
