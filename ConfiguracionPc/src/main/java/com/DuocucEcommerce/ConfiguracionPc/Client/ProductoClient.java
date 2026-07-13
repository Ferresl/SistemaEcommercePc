package com.DuocucEcommerce.ConfiguracionPc.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-service")
public interface ProductoClient {

    @GetMapping("/api/productos/{id}")
    ProductoResponseDTO producto(@PathVariable("id") Integer id);

    @GetMapping("/api/cpus/producto/{id}")
    CPUResponseDTO cpu(@PathVariable("id") Integer id);

    @GetMapping("/api/gpus/producto/{id}")
    GPUResponseDTO gpu(@PathVariable("id") Integer id);
}
