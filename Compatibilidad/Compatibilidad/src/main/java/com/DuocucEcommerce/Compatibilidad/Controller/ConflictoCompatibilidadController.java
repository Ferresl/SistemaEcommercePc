package com.DuocucEcommerce.Compatibilidad.Controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadCreateDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadUpdateDTO;
import com.DuocucEcommerce.Compatibilidad.Service.ConflictoCompatibilidadService;

@RestController
@RequestMapping("/api/compatibilidad/conflictos")
@RequiredArgsConstructor
public class ConflictoCompatibilidadController {
    private final ConflictoCompatibilidadService service;

    @GetMapping
    public ResponseEntity<List<ConflictoCompatibilidadResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @GetMapping("/{id}")
    public ResponseEntity<ConflictoCompatibilidadResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @PostMapping
    public ResponseEntity<ConflictoCompatibilidadResponseDTO> crear(@Valid @RequestBody ConflictoCompatibilidadCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    @PutMapping("/{id}")
    public ResponseEntity<ConflictoCompatibilidadResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ConflictoCompatibilidadUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}