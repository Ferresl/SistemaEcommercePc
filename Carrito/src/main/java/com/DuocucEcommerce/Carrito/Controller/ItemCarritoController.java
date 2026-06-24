package com.DuocucEcommerce.Carrito.Controller;

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

import com.DuocucEcommerce.Carrito.Dto.ItemCarritoCreateDTO;
import com.DuocucEcommerce.Carrito.Dto.ItemCarritoResponseDTO;
import com.DuocucEcommerce.Carrito.Dto.ItemCarritoUpdateDTO;
import com.DuocucEcommerce.Carrito.Service.ItemCarritoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Items del carrito", description = "Productos agregados a un carrito.")
@RestController
@RequestMapping("/api/items-carrito")
@RequiredArgsConstructor
public class ItemCarritoController {
    private final ItemCarritoService service;

    @Operation(summary = "Listar items del carrito", description = "Obtiene todos los registros de items del carrito.")
    @GetMapping
    public ResponseEntity<List<ItemCarritoResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @Operation(summary = "Buscar item del carrito por ID", description = "Obtiene un registro de item del carrito usando su identificador.")
    @GetMapping("/{id}")
    public ResponseEntity<ItemCarritoResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    @Operation(summary = "Crear item del carrito", description = "Registra un nuevo recurso de item del carrito.")
    @PostMapping
    public ResponseEntity<ItemCarritoResponseDTO> crear(@Valid @RequestBody ItemCarritoCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @Operation(summary = "Actualizar item del carrito", description = "Actualiza los datos de un registro de item del carrito.")
    @PutMapping("/{id}")
    public ResponseEntity<ItemCarritoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ItemCarritoUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    @Operation(summary = "Eliminar item del carrito", description = "Elimina un registro de item del carrito.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}
