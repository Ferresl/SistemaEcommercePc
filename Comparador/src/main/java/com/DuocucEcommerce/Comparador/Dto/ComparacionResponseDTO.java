package com.DuocucEcommerce.Comparador.Dto;

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
@Schema(description = "Respuesta entregada por la API para comparacion.")
public class ComparacionResponseDTO {
    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;
    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;
    @Schema(description = "Identificador de la categoria asociada.", example = "1")
    private Integer categoriaId;
    @Schema(description = "Productos incluidos en la comparacion.", example = "[]")
    private List<ItemComparacionResponseDTO> items;
}
