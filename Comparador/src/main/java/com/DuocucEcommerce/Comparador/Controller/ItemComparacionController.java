package com.DuocucEcommerce.Comparador.Controller;

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

import com.DuocucEcommerce.Comparador.Dto.ItemComparacionCreateDTO;
import com.DuocucEcommerce.Comparador.Dto.ItemComparacionResponseDTO;
import com.DuocucEcommerce.Comparador.Dto.ItemComparacionUpdateDTO;
import com.DuocucEcommerce.Comparador.Service.ItemComparacionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comparaciones/items")
@RequiredArgsConstructor
public class ItemComparacionController {
    private final ItemComparacionService service;

    @GetMapping
    public ResponseEntity<List<ItemComparacionResponseDTO>> listar() { 
        return ResponseEntity.ok(service.listar()); 
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ItemComparacionResponseDTO> buscarPorId(@PathVariable Integer id) {
         return ResponseEntity.ok(service.buscarPorId(id)); 
        }
    
    @PostMapping
    public ResponseEntity<ItemComparacionResponseDTO> crear(@Valid @RequestBody ItemComparacionCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ItemComparacionResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ItemComparacionUpdateDTO dto) { 
        return ResponseEntity.ok(service.actualizar(id, dto)); 
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { 
        service.eliminar(id); 
        return ResponseEntity.noContent().build(); 
    }
}