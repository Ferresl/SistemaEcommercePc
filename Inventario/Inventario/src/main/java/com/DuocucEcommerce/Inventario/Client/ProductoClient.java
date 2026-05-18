package com.DuocucEcommerce.Inventario.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.DuocucEcommerce.Inventario.Exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoClient {
    private final RestTemplate restTemplate;
    private final String baseUrl = "http://localhost:8083/api/productos";
    public ProductoResponseDTO obtenerPorId(Integer id) {
        try { return restTemplate.getForObject(baseUrl + "/" + id, ProductoResponseDTO.class); }
        catch (RestClientException ex) { throw new BadRequestException("Producto no encontrado"); }
    }
}