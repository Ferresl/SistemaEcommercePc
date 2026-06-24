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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Videojuegos", description = "Videojuegos usados en benchmarks y estimaciones.")
@RestController
@RequestMapping("/api/videojuegos")
@RequiredArgsConstructor
public class VideojuegoController {
    private final VideojuegoService service;

    @Operation(summary = "Listar videojuegos", description = "Obtiene todos los registros de videojuegos.")
    @GetMapping
    public ResponseEntity<List<VideojuegoResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    @Operation(summary = "Buscar videojuego por ID", description = "Obtiene un registro de videojuego usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<VideojuegoResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    @Operation(summary = "Crear videojuego", description = "Registra un nuevo recurso de videojuego.")
    @PostMapping
    public ResponseEntity<VideojuegoResponseDTO> crear(@Valid @RequestBody VideojuegoCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    @Operation(summary = "Actualizar videojuego", description = "Actualiza los datos de un registro de videojuego.")
    @PutMapping("/{id}")
    public ResponseEntity<VideojuegoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody VideojuegoUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    @Operation(summary = "Eliminar videojuego", description = "Elimina un registro de videojuego.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
