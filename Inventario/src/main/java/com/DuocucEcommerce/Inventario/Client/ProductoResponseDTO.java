package com.DuocucEcommerce.Inventario.Client;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta entregada por la API para producto.")
public class ProductoResponseDTO {
    @Schema(description = "Identificador unico del registro.", example = "1")
    private Integer id;
    @Schema(description = "Nombre comercial del producto.", example = "Procesador Ryzen 5 7600")
    private String nombre;
    @Schema(description = "Marca comercial del producto.", example = "AMD")
    private String marca;
    @Schema(description = "Modelo especifico del producto.", example = "Ryzen 5 7600")
    private String modelo;
    @Schema(description = "Precio del producto.", example = "249990")
    private BigDecimal precio;
    @Schema(description = "URL de la imagen asociada al producto.", example = "https://ejemplo.cl/producto.png")
    private String imagenUrl;
    @Schema(description = "Identificador de la categoria asociada.", example = "1")
    private Integer categoriaId;
    @Schema(description = "Estado de disponibilidad del producto.", example = "ACTIVO")
    private String estado;
}
