package com.DuocucEcommerce.Carrito.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioResponseDTO { private Integer id; private Integer productoId; private Integer stockDisponible; private Integer stockReservado; private Integer stockMinimo; }

