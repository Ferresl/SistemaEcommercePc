package com.DuocucEcommerce.ConfiguracionPc.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.DuocucEcommerce.ConfiguracionPc.Exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoClient {
    private final RestTemplate restTemplate;
    private <T> T get(String url, Class<T> c) { try { return restTemplate.getForObject(url, c); } catch (RestClientException ex) { throw new BadRequestException("Producto o ficha no encontrado"); } }
    public ProductoResponseDTO producto(Integer id) { return get("http://localhost:8083/api/productos/" + id, ProductoResponseDTO.class); }
    public CPUResponseDTO cpu(Integer id) { return get("http://localhost:8083/api/cpus/producto/" + id, CPUResponseDTO.class); }
    public GPUResponseDTO gpu(Integer id) { return get("http://localhost:8083/api/gpus/producto/" + id, GPUResponseDTO.class); }
}
