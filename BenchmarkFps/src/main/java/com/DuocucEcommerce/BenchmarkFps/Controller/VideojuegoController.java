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

import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoCreateDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoResponseDTO;
import com.DuocucEcommerce.BenchmarkFps.Dto.VideojuegoUpdateDTO;
import com.DuocucEcommerce.BenchmarkFps.Service.VideojuegoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/videojuegos")
@RequiredArgsConstructor
public class VideojuegoController {
    private final VideojuegoService service;

    @GetMapping
    public ResponseEntity<List<VideojuegoResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @PostMapping
    public ResponseEntity<VideojuegoResponseDTO> crear(@Valid @RequestBody VideojuegoCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody VideojuegoUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
