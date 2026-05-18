package com.DuocucEcommerce.Compatibilidad.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DuocucEcommerce.Compatibilidad.Dto.CompatibilidadRequestDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.CompatibilidadResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Service.CompatibilidadService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/compatibilidad")
@RequiredArgsConstructor
public class CompatibilidadController {
    private final CompatibilidadService service;
    
    @PostMapping("/evaluar") 
    public ResponseEntity<CompatibilidadResponseDTO> evaluar(@Valid @RequestBody CompatibilidadRequestDTO dto) { 
        return ResponseEntity.ok(service.evaluar(dto)); 
    }
    
    @GetMapping("/configuracion/{configuracionId}") 
    public ResponseEntity<CompatibilidadResponseDTO> buscarPorConfiguracion(@PathVariable Integer configuracionId) { 
        return ResponseEntity.ok(service.buscarPorConfiguracion(configuracionId)); 
    }
    
    @GetMapping("/resultados/{id}") 
    public ResponseEntity<CompatibilidadResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
}
