package com.DuocucEcommerce.Compatibilidad.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecomendacionComponenteResponseDTO {

    private Integer id;

    private Integer conflictoId;

    private Integer productoRecomendadoId;

    private String motivo;

}
