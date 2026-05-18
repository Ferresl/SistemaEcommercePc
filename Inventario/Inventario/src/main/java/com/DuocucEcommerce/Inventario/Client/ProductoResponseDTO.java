package com.DuocucEcommerce.Inventario.Client;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDTO {
    private Integer id;
    private String nombre;
    private String marca;
    private String modelo;
    private BigDecimal precio;
    private String imagenUrl;
    private Integer categoriaId;
    private String estado;
}
