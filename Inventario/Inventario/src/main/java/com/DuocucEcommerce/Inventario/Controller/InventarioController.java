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

@RestController
@RequestMapping("/api/inventarios")
@RequiredArgsConstructor
public class InventarioController {
    private final InventarioService service;
    @GetMapping public ResponseEntity<List<InventarioResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @GetMapping("/{id}") public ResponseEntity<InventarioResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @GetMapping("/producto/{productoId}") public ResponseEntity<InventarioResponseDTO> buscarPorProducto(@PathVariable Integer productoId) { return ResponseEntity.ok(service.buscarPorProducto(productoId)); }
    @PostMapping public ResponseEntity<InventarioResponseDTO> crear(@Valid @RequestBody InventarioCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    @PutMapping("/{id}") public ResponseEntity<InventarioResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody InventarioUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    @PutMapping("/descontar/{productoId}") public ResponseEntity<InventarioResponseDTO> descontar(@PathVariable Integer productoId, @RequestParam Integer cantidad) { return ResponseEntity.ok(service.descontar(productoId, cantidad)); }
    @PutMapping("/reponer/{productoId}") public ResponseEntity<InventarioResponseDTO> reponer(@PathVariable Integer productoId, @RequestParam Integer cantidad) { return ResponseEntity.ok(service.reponer(productoId, cantidad)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
