package com.DuocucEcommerce.Compatibilidad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConflictoCompatibilidadResponseDTO {

    private Integer id;

    private Integer resultadoId;

    private Integer productoAId;

    private Integer productoBId;

    private String motivo;

}