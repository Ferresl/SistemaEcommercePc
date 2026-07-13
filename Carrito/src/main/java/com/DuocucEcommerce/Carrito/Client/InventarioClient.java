package com.DuocucEcommerce.Carrito.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventario-service")
public interface InventarioClient {

    @GetMapping("/api/inventarios/producto/{productoId}")
    InventarioResponseDTO obtenerPorProducto(@PathVariable("productoId") Integer productoId);
}
