package com.DuocucEcommerce.Comparador.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemComparacionUpdateDTO {

    @NotNull(message = "El campo comparacionId es obligatorio")
    private Integer comparacionId;

    @NotNull(message = "El campo productoId es obligatorio")
    private Integer productoId;

}