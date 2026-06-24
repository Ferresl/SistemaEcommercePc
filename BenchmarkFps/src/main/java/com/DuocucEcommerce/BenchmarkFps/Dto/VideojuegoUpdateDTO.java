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
@Schema(description = "Datos permitidos para actualizar videojuego.")
public class VideojuegoUpdateDTO {

    @NotBlank(message = "El campo nombre es obligatorio")
    @Schema(description = "Nombre del videojuego.", example = "Cyberpunk 2077")
    private String nombre;

}
