package com.DuocucEcommerce.ConfiguracionPc.Dto;

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
@Schema(description = "Datos permitidos para actualizar configuracion pcupdate.")
public class ConfiguracionPCUpdateDTO {
    @Schema(description = "Identificador del usuario asociado.", example = "1")
    @NotNull private Integer usuarioId;
    @Schema(description = "Nombre del registro o del usuario.", example = "Juan")
    @NotBlank private String nombre;
    @Schema(description = "Identificador del procesador seleccionado.", example = "1")
    @NotNull private Integer cpuId;
    @Schema(description = "Identificador de la tarjeta grafica seleccionada.", example = "1")
    @NotNull private Integer gpuId;
    @Schema(description = "Identificador de la memoria RAM seleccionada.", example = "1")
    @NotNull private Integer ramId;
    @Schema(description = "Identificador de la placa madre seleccionada.", example = "1")
    @NotNull private Integer placaMadreId;
    @Schema(description = "Identificador del almacenamiento seleccionado.", example = "1")
    @NotNull private Integer almacenamientoId;
    @Schema(description = "Identificador de la fuente de poder seleccionada.", example = "1")
    @NotNull private Integer fuentePoderId;
    @Schema(description = "Identificador del gabinete seleccionado.", example = "1")
    @NotNull private Integer gabineteId;
    @Schema(description = "Estado actual del registro.", example = "ACTIVO")
    private String estado;
}
