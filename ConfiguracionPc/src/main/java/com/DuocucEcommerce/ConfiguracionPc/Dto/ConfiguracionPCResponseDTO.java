package com.DuocucEcommerce.ConfiguracionPc.Dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Respuesta entregada por la API para configuracion de PC.")
public class ConfiguracionPCResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;

    @Schema(description = "Nombre asignado a la configuracion de PC.", example = "PC gamer principal")
    private String nombre;

    @Schema(description = "Identificador del procesador seleccionado.", example = "1")
    private Integer cpuId;

    @Schema(description = "Identificador de la tarjeta grafica seleccionada.", example = "1")
    private Integer gpuId;

    @Schema(description = "Identificador de la memoria RAM seleccionada.", example = "1")
    private Integer ramId;

    @Schema(description = "Identificador de la placa madre seleccionada.", example = "1")
    private Integer placaMadreId;

    @Schema(description = "Identificador del almacenamiento seleccionado.", example = "1")
    private Integer almacenamientoId;

    @Schema(description = "Identificador de la fuente de poder seleccionada.", example = "1")
    private Integer fuentePoderId;

    @Schema(description = "Identificador del gabinete seleccionado.", example = "1")
    private Integer gabineteId;

    @Schema(description = "Precio total estimado de la configuracion.", example = "899990")
    private BigDecimal precioTotal;

    @Schema(description = "Consumo total estimado de la configuracion en watts.", example = "520")
    private Integer tdpTotal;

    @Schema(description = "Estado actual de la compatibilidad o configuracion.", example = "COMPATIBLE")
    private String estado;

}
