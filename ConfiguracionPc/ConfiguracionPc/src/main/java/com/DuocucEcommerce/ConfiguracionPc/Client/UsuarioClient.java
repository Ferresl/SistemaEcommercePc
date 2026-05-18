package com.DuocucEcommerce.ConfiguracionPc.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.DuocucEcommerce.ConfiguracionPc.Exception.BadRequestException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioClient { private final RestTemplate restTemplate; public void validar(Integer id) { try { restTemplate.getForObject("http://localhost:8082/api/usuarios/" + id, Object.class); } catch (RestClientException ex) { throw new BadRequestException("Usuario no encontrado"); } } }
