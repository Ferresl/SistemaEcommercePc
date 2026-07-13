package com.DuocucEcommerce.ConfiguracionPc.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "compatibilidad-service")
public interface CompatibilidadClient {

    @PostMapping("/api/compatibilidad/evaluar")
    CompatibilidadResponseDTO evaluar(@RequestBody CompatibilidadRequestDTO dto);
}

