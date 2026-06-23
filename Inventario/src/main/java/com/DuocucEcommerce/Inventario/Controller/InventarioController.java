package com.DuocucEcommerce.Inventario.Controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.DuocucEcommerce.Inventario.Dto.InventarioCreateDTO;
import com.DuocucEcommerce.Inventario.Dto.InventarioResponseDTO;
import com.DuocucEcommerce.Inventario.Dto.InventarioUpdateDTO;
import com.DuocucEcommerce.Inventario.Service.InventarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Inventario", description = "Stock disponible para cada producto.")
@RestController
@RequestMapping("/api/inventarios")
@RequiredArgsConstructor
public class InventarioController {
    private final InventarioService service;
    
    @Operation(summary = "Listar inventario", description = "Obtiene todos los registros de inventario.")
    @GetMapping 
    public ResponseEntity<List<InventarioResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @Operation(summary = "Buscar inventario por ID", description = "Obtiene un registro de inventario usando su identificador.")
    @GetMapping("/{id}") 
    public ResponseEntity<InventarioResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    @Operation(summary = "Buscar inventario por producto", description = "Obtiene inventario asociados a un producto.")
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<InventarioResponseDTO> buscarPorProducto(@PathVariable Integer productoId) { 
        return ResponseEntity.ok(service.buscarPorProducto(productoId)); 
    }
    
    @Operation(summary = "Crear inventario", description = "Registra un nuevo recurso de inventario.")
    @PostMapping 
    public ResponseEntity<InventarioResponseDTO> crear(@Valid @RequestBody InventarioCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @Operation(summary = "Actualizar inventario", description = "Actualiza los datos de un registro de inventario.")
    @PutMapping("/{id}") 
    public ResponseEntity<InventarioResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody InventarioUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    @Operation(summary = "Actualizar inventario", description = "Actualiza informacion existente de inventario.")
    @PutMapping("/descontar/{productoId}") 
    public ResponseEntity<InventarioResponseDTO> descontar(@PathVariable Integer productoId, @RequestParam Integer cantidad) { 
        return ResponseEntity.ok(service.descontar(productoId, cantidad)); 
    }
    
    @Operation(summary = "Actualizar inventario", description = "Actualiza informacion existente de inventario.")
    @PutMapping("/reponer/{productoId}") 
    public ResponseEntity<InventarioResponseDTO> reponer(@PathVariable Integer productoId, @RequestParam Integer cantidad) { 
        return ResponseEntity.ok(service.reponer(productoId, cantidad)); 
    }
    
    @Operation(summary = "Eliminar inventario", description = "Elimina un registro de inventario.")
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
