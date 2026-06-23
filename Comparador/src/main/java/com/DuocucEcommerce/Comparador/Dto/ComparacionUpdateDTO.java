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
public class ComparacionUpdateDTO {

    @NotNull(message = "El campo usuarioId es obligatorio")
    private Integer usuarioId;

    @NotNull(message = "El campo categoriaId es obligatorio")
    private Integer categoriaId;

}