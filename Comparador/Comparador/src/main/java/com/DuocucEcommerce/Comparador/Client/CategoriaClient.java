package com.DuocucEcommerce.Comparador.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.DuocucEcommerce.Comparador.Exception.BadRequestException;

@Component
@RequiredArgsConstructor
public class CategoriaClient { private final RestTemplate restTemplate; public void validar(Integer id) { try { restTemplate.getForObject("http://localhost:8084/api/categorias/" + id, Object.class); } catch (RestClientException ex) { throw new BadRequestException("Categoria no encontrada"); } } }

