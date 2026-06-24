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

import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.EstimacionFPSUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Service.EstimacionFPSService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Estimaciones FPS", description = "Historial de estimaciones de FPS.")
@RestController
@RequestMapping("/api/fps")
@RequiredArgsConstructor
public class EstimacionFPSController {
    private final EstimacionFPSService service;

    @Operation(summary = "Listar estimaciones fps", description = "Obtiene todos los registros de estimaciones fps.")
    @GetMapping
    public ResponseEntity<List<EstimacionFPSResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    @Operation(summary = "Buscar estimacion FPS por ID", description = "Obtiene un registro de estimacion FPS usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<EstimacionFPSResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    @Operation(summary = "Crear estimacion FPS", description = "Registra un nuevo recurso de estimacion FPS.")
    @PostMapping
    public ResponseEntity<EstimacionFPSResponseDTO> crear(@Valid @RequestBody EstimacionFPSCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    @Operation(summary = "Actualizar estimacion FPS", description = "Actualiza los datos de un registro de estimacion FPS.")
    @PutMapping("/{id}")
    public ResponseEntity<EstimacionFPSResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody EstimacionFPSUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    @Operation(summary = "Eliminar estimacion FPS", description = "Elimina un registro de estimacion FPS.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
