package com.DuocucEcommerce.Carrito.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.DuocucEcommerce.Carrito.Exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventarioClient {
    private final RestTemplate restTemplate;
    public InventarioResponseDTO obtenerPorProducto(Integer productoId) { try { return restTemplate.getForObject("http://localhost:8085/api/inventarios/producto/" + productoId, InventarioResponseDTO.class); } catch (RestClientException ex) { throw new BadRequestException("Inventario no encontrado"); } }
}
