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

@RestController
@RequestMapping("/api/fps")
@RequiredArgsConstructor
public class EstimacionFPSController {
    private final EstimacionFPSService service;

    @GetMapping
    public ResponseEntity<List<EstimacionFPSResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    @GetMapping("/{id}")
    public ResponseEntity<EstimacionFPSResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    @PostMapping
    public ResponseEntity<EstimacionFPSResponseDTO> crear(@Valid @RequestBody EstimacionFPSCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    @PutMapping("/{id}")
    public ResponseEntity<EstimacionFPSResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody EstimacionFPSUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
