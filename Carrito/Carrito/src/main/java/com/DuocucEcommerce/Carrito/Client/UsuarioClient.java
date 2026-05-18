package com.DuocucEcommerce.Carrito.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.DuocucEcommerce.Carrito.Exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioClient {
    private final RestTemplate restTemplate;
    public UsuarioResponseDTO obtenerPorId(Integer id) { try { return restTemplate.getForObject("http://localhost:8082/api/usuarios/" + id, UsuarioResponseDTO.class); } catch (RestClientException ex) { throw new BadRequestException("Usuario no encontrado"); } }
}