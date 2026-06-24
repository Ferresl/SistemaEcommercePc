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
@Schema(description = "Datos necesarios para crear comparacion.")
public class ComparacionCreateDTO {

    @NotNull(message = "El campo usuarioId es obligatorio")
    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;

    @NotNull(message = "El campo categoriaId es obligatorio")
    @Schema(description = "Identificador de la categoria asociada.", example = "1")
    private Integer categoriaId;

}
