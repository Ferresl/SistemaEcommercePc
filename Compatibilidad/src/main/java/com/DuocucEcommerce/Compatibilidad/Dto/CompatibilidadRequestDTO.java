package com.DuocucEcommerce.Compatibilidad.Dto;

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
@Schema(description = "Datos de entrada para compatibilidad.")
public class CompatibilidadRequestDTO {
    @Schema(description = "Identificador de la configuracion de PC.", example = "1")
    @NotNull private Integer configuracionId;
    @Schema(description = "Identificador del procesador seleccionado.", example = "1")
    @NotNull private Integer cpuId;
    @Schema(description = "Identificador de la tarjeta grafica seleccionada.", example = "1")
    @NotNull private Integer gpuId;
    @Schema(description = "Identificador de la memoria RAM seleccionada.", example = "1")
    @NotNull private Integer ramId;
    @Schema(description = "Identificador de la placa madre seleccionada.", example = "1")
    @NotNull private Integer placaMadreId;
    @Schema(description = "Identificador de la fuente de poder seleccionada.", example = "1")
    @NotNull private Integer fuentePoderId;
    @Schema(description = "Identificador del gabinete seleccionado.", example = "1")
    @NotNull private Integer gabineteId;
    @Schema(description = "Consumo total estimado de la configuracion en watts.", example = "520")
    @NotNull private Integer tdpTotal;
}
