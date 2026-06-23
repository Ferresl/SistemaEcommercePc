package com.DuocucEcommerce.Categoria.Dto;

import com.DuocucEcommerce.Categoria.Model.TipoProducto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaUpdateDTO {

    @NotBlank(message = "El campo nombre es obligatorio")
    @Size(min = 3, max = 80)
    private String nombre;

    @NotNull(message = "El campo tipoProducto es obligatorio")
    private TipoProducto tipoProducto;

}
