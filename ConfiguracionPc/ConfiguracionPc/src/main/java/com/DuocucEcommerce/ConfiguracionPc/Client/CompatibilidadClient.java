package com.DuocucEcommerce.ConfiguracionPc.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.DuocucEcommerce.ConfiguracionPc.Exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CompatibilidadClient { private final RestTemplate restTemplate; public CompatibilidadResponseDTO evaluar(CompatibilidadRequestDTO dto) { try { return restTemplate.postForObject("http://localhost:8089/api/compatibilidad/evaluar", dto, CompatibilidadResponseDTO.class); } catch (RestClientException ex) { throw new BadRequestException("No se pudo evaluar compatibilidad"); } } }

