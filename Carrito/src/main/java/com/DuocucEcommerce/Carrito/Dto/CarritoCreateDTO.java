package com.DuocucEcommerce.Carrito.Dto;

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
@Schema(description = "Datos necesarios para crear carrito.")
public class CarritoCreateDTO {
    @NotNull(message = "El usuario es obligatorio")
    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;
}
