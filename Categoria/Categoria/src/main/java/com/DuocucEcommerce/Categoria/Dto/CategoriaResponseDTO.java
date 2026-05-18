package com.DuocucEcommerce.Categoria.Dto;

import com.DuocucEcommerce.Categoria.Model.TipoProducto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaResponseDTO {

    private Integer id;

    private String nombre;

    private TipoProducto tipoProducto;

}
