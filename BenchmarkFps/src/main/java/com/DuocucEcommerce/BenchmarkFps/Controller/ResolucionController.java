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

@RestController
@RequestMapping("/api/resoluciones")
@RequiredArgsConstructor
public class ResolucionController {
    private final ResolucionService service;

    @GetMapping
    public ResponseEntity<List<ResolucionResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResolucionResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    @PostMapping
    public ResponseEntity<ResolucionResponseDTO> crear(@Valid @RequestBody ResolucionCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResolucionResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ResolucionUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
         service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
