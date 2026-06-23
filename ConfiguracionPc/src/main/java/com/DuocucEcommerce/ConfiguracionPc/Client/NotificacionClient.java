package com.DuocucEcommerce.ConfiguracionPc.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificacionClient { private final RestTemplate restTemplate; public void crear(NotificacionCreateDTO dto) { try { restTemplate.postForObject("http://localhost:8092/api/notificaciones", dto, Object.class); } catch (Exception ex) { } } }

