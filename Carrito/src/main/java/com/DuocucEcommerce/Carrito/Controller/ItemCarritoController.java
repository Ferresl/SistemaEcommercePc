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

@RestController
@RequestMapping("/api/items-carrito")
@RequiredArgsConstructor
public class ItemCarritoController {
    private final ItemCarritoService service;

    @GetMapping
    public ResponseEntity<List<ItemCarritoResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ItemCarritoResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    @PostMapping
    public ResponseEntity<ItemCarritoResponseDTO> crear(@Valid @RequestBody ItemCarritoCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ItemCarritoResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ItemCarritoUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}