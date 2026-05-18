package com.DuocucEcommerce.Comparador.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemComparacionResponseDTO {

    private Integer id;

    private Integer comparacionId;

    private Integer productoId;

}