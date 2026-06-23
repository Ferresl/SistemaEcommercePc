package com.DuocucEcommerce.Comparador.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.DuocucEcommerce.Comparador.Exception.BadRequestException;

@Component
@RequiredArgsConstructor
public class ProductoClient { private final RestTemplate restTemplate; public ProductoResponseDTO obtener(Integer id) { try { return restTemplate.getForObject("http://localhost:8083/api/productos/" + id, ProductoResponseDTO.class); } catch (RestClientException ex) { throw new BadRequestException("Producto no encontrado"); } } }
