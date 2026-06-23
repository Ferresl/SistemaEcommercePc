package com.DuocucEcommerce.Compatibilidad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoCompatibilidadResponseDTO {

    private Integer id;

    private Integer configuracionId;

    private String estado;

    private String mensaje;

}