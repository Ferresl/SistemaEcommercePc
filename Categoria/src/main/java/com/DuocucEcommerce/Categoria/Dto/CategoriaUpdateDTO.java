package com.DuocucEcommerce.Categoria.Dto;

import com.DuocucEcommerce.Categoria.Model.TipoProducto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Datos permitidos para actualizar categoria.")
public class CategoriaUpdateDTO {

    @NotBlank(message = "El campo nombre es obligatorio")
    @Size(min = 3, max = 80)
    @Schema(description = "Nombre de la categoria.", example = "Procesadores")
    private String nombre;

    @NotNull(message = "El campo tipoProducto es obligatorio")
    @Schema(description = "Tipo de producto al que pertenece la categoria.", example = "COMPONENTE")
    private TipoProducto tipoProducto;

}
