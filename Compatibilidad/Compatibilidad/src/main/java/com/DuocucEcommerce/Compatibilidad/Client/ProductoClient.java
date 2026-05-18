package com.DuocucEcommerce.Compatibilidad.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.DuocucEcommerce.Compatibilidad.Exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductoClient {
    private final RestTemplate restTemplate;
    private <T> T get(String url, Class<T> clase) { try { return restTemplate.getForObject(url, clase); } catch (RestClientException ex) { throw new BadRequestException("No se pudo obtener ficha tecnica"); } }
    public CPUResponseDTO cpu(Integer id) { return get("http://localhost:8083/api/cpus/producto/" + id, CPUResponseDTO.class); }
    public GPUResponseDTO gpu(Integer id) { return get("http://localhost:8083/api/gpus/producto/" + id, GPUResponseDTO.class); }
    public RAMResponseDTO ram(Integer id) { return get("http://localhost:8083/api/rams/producto/" + id, RAMResponseDTO.class); }
    public PlacaMadreResponseDTO placa(Integer id) { return get("http://localhost:8083/api/placas-madre/producto/" + id, PlacaMadreResponseDTO.class); }
    public FuentePoderResponseDTO fuente(Integer id) { return get("http://localhost:8083/api/fuentes/producto/" + id, FuentePoderResponseDTO.class); }
    public GabineteResponseDTO gabinete(Integer id) { return get("http://localhost:8083/api/gabinetes/producto/" + id, GabineteResponseDTO.class); }
}