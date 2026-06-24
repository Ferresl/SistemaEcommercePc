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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Compatibilidad", description = "Evaluacion de compatibilidad entre componentes.")
@RestController
@RequestMapping("/api/compatibilidad")
@RequiredArgsConstructor
public class CompatibilidadController {
    private final CompatibilidadService service;
    
    @Operation(summary = "Evaluar compatibilidad", description = "Evalua la compatibilidad de una configuracion de PC.")
    @PostMapping("/evaluar") 
    public ResponseEntity<CompatibilidadResponseDTO> evaluar(@Valid @RequestBody CompatibilidadRequestDTO dto) { 
        return ResponseEntity.ok(service.evaluar(dto)); 
    }
    
    @Operation(summary = "Buscar resultado por configuracion", description = "Obtiene el resultado de compatibilidad de una configuracion.")
    @GetMapping("/configuracion/{configuracionId}") 
    public ResponseEntity<CompatibilidadResponseDTO> buscarPorConfiguracion(@PathVariable Integer configuracionId) { 
        return ResponseEntity.ok(service.buscarPorConfiguracion(configuracionId)); 
    }
    
    @Operation(summary = "Buscar compatibilidad por ID", description = "Obtiene un registro de compatibilidad usando su identificador.")
    @GetMapping("/resultados/{id}") 
    public ResponseEntity<CompatibilidadResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
}
