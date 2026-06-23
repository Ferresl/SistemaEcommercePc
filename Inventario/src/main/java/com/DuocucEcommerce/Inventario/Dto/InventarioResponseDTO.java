package com.DuocucEcommerce.Inventario.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioResponseDTO {

    private Integer id;

    private Integer productoId;

    private Integer stockDisponible;

    private Integer stockReservado;

    private Integer stockMinimo;

}
