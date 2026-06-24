package com.DuocucEcommerce.Carrito.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta entregada por la API para inventario.")
public class InventarioResponseDTO { private Integer id; private Integer productoId; private Integer stockDisponible; private Integer stockReservado; private Integer stockMinimo; }

