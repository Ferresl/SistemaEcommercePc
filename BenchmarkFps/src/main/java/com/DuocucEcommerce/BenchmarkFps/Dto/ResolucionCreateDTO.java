package com.DuocucEcommerce.BenchmarkFps.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos necesarios para crear resolucion.")
public class ResolucionCreateDTO {

    @NotBlank(message = "El campo nombre es obligatorio")
    @Schema(description = "Nombre de la resolucion.", example = "1920x1080")
    private String nombre;

}
