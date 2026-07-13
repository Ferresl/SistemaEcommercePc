package com.DuocucEcommerce.Compatibilidad.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-service")
public interface ProductoClient {

    @GetMapping("/api/cpus/producto/{id}")
    CPUResponseDTO cpu(@PathVariable("id") Integer id);

    @GetMapping("/api/gpus/producto/{id}")
    GPUResponseDTO gpu(@PathVariable("id") Integer id);

    @GetMapping("/api/rams/producto/{id}")
    RAMResponseDTO ram(@PathVariable("id") Integer id);

    @GetMapping("/api/placas-madre/producto/{id}")
    PlacaMadreResponseDTO placa(@PathVariable("id") Integer id);

    @GetMapping("/api/fuentes/producto/{id}")
    FuentePoderResponseDTO fuente(@PathVariable("id") Integer id);

    @GetMapping("/api/gabinetes/producto/{id}")
    GabineteResponseDTO gabinete(@PathVariable("id") Integer id);
}
