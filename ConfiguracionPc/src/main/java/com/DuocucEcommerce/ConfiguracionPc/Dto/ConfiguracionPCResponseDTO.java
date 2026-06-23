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
@Schema(description = "Respuesta entregada por la API para configuracion pcresponse.")
public class ConfiguracionPCResponseDTO {

    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;

    @Schema(description = "Identificador del usuario asociado.", example = "1")
    private Integer usuarioId;

    @Schema(description = "Nombre del registro o del usuario.", example = "Juan")
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

    @Schema(description = "Valor de precio total.", example = "249990")
    private BigDecimal precioTotal;

    @Schema(description = "Valor de tdp total.", example = "1")
    private Integer tdpTotal;

    @Schema(description = "Estado actual del registro.", example = "ACTIVO")
    private String estado;

}
