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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Conflictos de compatibilidad", description = "Conflictos detectados entre componentes.")
@RestController
@RequestMapping("/api/compatibilidad/conflictos")
@RequiredArgsConstructor
public class ConflictoCompatibilidadController {
    private final ConflictoCompatibilidadService service;

    @Operation(summary = "Listar conflictos de compatibilidad", description = "Obtiene todos los registros de conflictos de compatibilidad.")
    @GetMapping
    public ResponseEntity<List<ConflictoCompatibilidadResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @Operation(summary = "Buscar conflicto de compatibilidad por ID", description = "Obtiene un registro de conflicto de compatibilidad usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<ConflictoCompatibilidadResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    @Operation(summary = "Crear conflicto de compatibilidad", description = "Registra un nuevo recurso de conflicto de compatibilidad.")
    @PostMapping
    public ResponseEntity<ConflictoCompatibilidadResponseDTO> crear(@Valid @RequestBody ConflictoCompatibilidadCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    @Operation(summary = "Actualizar conflicto de compatibilidad", description = "Actualiza los datos de un registro de conflicto de compatibilidad.")
    @PutMapping("/{id}")
    public ResponseEntity<ConflictoCompatibilidadResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ConflictoCompatibilidadUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    @Operation(summary = "Eliminar conflicto de compatibilidad", description = "Elimina un registro de conflicto de compatibilidad.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
