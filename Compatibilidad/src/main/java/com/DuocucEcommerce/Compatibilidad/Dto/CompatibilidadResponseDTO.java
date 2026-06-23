package com.DuocucEcommerce.Compatibilidad.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompatibilidadResponseDTO {
    private Integer resultadoId;
    private Integer configuracionId;
    private String estado;
    private String mensaje;
    private List<ConflictoCompatibilidadResponseDTO> conflictos;
    private List<RecomendacionComponenteResponseDTO> recomendaciones;
}
