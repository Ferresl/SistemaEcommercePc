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

import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.BenchmarkUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Service.BenchmarkService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/benchmarks")
@RequiredArgsConstructor
public class BenchmarkController {
    private final BenchmarkService service;

    @GetMapping
    public ResponseEntity<List<BenchmarkResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @GetMapping("/{id}")
    public ResponseEntity<BenchmarkResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @PostMapping
    public ResponseEntity<BenchmarkResponseDTO> crear(@Valid @RequestBody BenchmarkCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    @PutMapping("/{id}")
    public ResponseEntity<BenchmarkResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody BenchmarkUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
