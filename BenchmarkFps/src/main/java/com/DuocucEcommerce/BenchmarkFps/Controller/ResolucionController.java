package com.DuocucEcommerce.BenchmarkFps.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.ResolucionUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Service.ResolucionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Resoluciones", description = "Resoluciones disponibles para pruebas de rendimiento.")
@RestController
@RequestMapping("/api/resoluciones")
@RequiredArgsConstructor
public class ResolucionController {
    private final ResolucionService service;

    @Operation(summary = "Listar resoluciones", description = "Obtiene todos los registros de resoluciones.")
    @GetMapping
    public ResponseEntity<List<ResolucionResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    @Operation(summary = "Buscar resolucion por ID", description = "Obtiene un registro de resolucion usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<ResolucionResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    @Operation(summary = "Crear resolucion", description = "Registra un nuevo recurso de resolucion.")
    @PostMapping
    public ResponseEntity<ResolucionResponseDTO> crear(@Valid @RequestBody ResolucionCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    @Operation(summary = "Actualizar resolucion", description = "Actualiza los datos de un registro de resolucion.")
    @PutMapping("/{id}")
    public ResponseEntity<ResolucionResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ResolucionUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    @Operation(summary = "Eliminar resolucion", description = "Elimina un registro de resolucion.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
         service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
