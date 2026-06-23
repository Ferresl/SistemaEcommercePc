package com.DuocucEcommerce.Compatibilidad.Controller;

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

import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadCreateDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadResponseDTO;
import com.DuocucEcommerce.Compatibilidad.Dto.ConflictoCompatibilidadUpdateDTO;
import com.DuocucEcommerce.Compatibilidad.Service.ConflictoCompatibilidadService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/compatibilidad/conflictos")
@RequiredArgsConstructor
public class ConflictoCompatibilidadController {
    private final ConflictoCompatibilidadService service;

    @GetMapping
    public ResponseEntity<List<ConflictoCompatibilidadResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ConflictoCompatibilidadResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    @PostMapping
    public ResponseEntity<ConflictoCompatibilidadResponseDTO> crear(@Valid @RequestBody ConflictoCompatibilidadCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    @PutMapping("/{id}")
    public ResponseEntity<ConflictoCompatibilidadResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ConflictoCompatibilidadUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}