package com.DuocucEcommerce.Compatibilidad.Dto;

import jakarta.validation.constraints.NotBlank;
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
@Schema(description = "Datos necesarios para crear resultado de compatibilidad.")
public class ResultadoCompatibilidadCreateDTO {

    @NotNull(message = "El campo configuracionId es obligatorio")
    @Schema(description = "Identificador de la configuracion de PC.", example = "1")
    private Integer configuracionId;

    @NotBlank(message = "El campo estado es obligatorio")
    @Schema(description = "Estado actual de la compatibilidad o configuracion.", example = "COMPATIBLE")
    private String estado;

    @NotBlank(message = "El campo mensaje es obligatorio")
    @Schema(description = "Mensaje descriptivo del resultado de compatibilidad.", example = "La configuracion es compatible")
    private String mensaje;

}
