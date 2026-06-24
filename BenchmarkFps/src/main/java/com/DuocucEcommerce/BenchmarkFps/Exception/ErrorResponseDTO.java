package com.DuocucEcommerce.BenchmarkFps.Exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta usada cuando la API informa un error.")
public class ErrorResponseDTO {
    @Schema(description = "Detalle del error retornado por la API.", example = "No se encontro el recurso solicitado")
    private String mensaje;
    @Schema(description = "Codigo HTTP asociado al error.", example = "404")
    private Integer status;
    @Schema(description = "Fecha y hora en que se genero la respuesta.", example = "2026-06-23T15:30:00")
    private LocalDateTime timestamp;
}
