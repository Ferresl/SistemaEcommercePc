package com.DuocucEcommerce.Carrito.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarritoCreateDTO {
    @NotNull(message = "El usuario es obligatorio")
    private Integer usuarioId;
}