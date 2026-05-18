package com.DuocucEcommerce.Comparador.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DuocucEcommerce.Comparador.Dto.AgregarItemComparacionDTO;
import com.DuocucEcommerce.Comparador.Dto.ComparacionCreateDTO;
import com.DuocucEcommerce.Comparador.Dto.ComparacionResponseDTO;
import com.DuocucEcommerce.Comparador.Service.ComparacionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comparaciones")
@RequiredArgsConstructor
public class ComparacionController {
    private final ComparacionService service;
    
    @GetMapping("/{id}") public ResponseEntity<ComparacionResponseDTO> buscarPorId(@PathVariable Integer id) { 
        return ResponseEntity.ok(service.buscarPorId(id)); 
    }
    
    @GetMapping("/usuario/{usuarioId}") public ResponseEntity<List<ComparacionResponseDTO>> listarPorUsuario(@PathVariable Integer usuarioId) { 
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId)); 
    }
    
    @PostMapping public ResponseEntity<ComparacionResponseDTO> crear(@Valid @RequestBody ComparacionCreateDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); 
    }
    
    @PostMapping("/{id}/items") public ResponseEntity<ComparacionResponseDTO> agregarItem(@PathVariable Integer id, @Valid @RequestBody AgregarItemComparacionDTO dto) { 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.agregarItem(id, dto)); 
    }
    
    @DeleteMapping("/items/{itemId}") public ResponseEntity<Void> eliminarItem(@PathVariable Integer itemId) { 
        service.eliminarItem(itemId); 
        return ResponseEntity.noContent().build(); 
    }
}