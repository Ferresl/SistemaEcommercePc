package com.DuocucEcommerce.ConfiguracionPc.Controller;

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

import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCCreateDTO;
import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCResponseDTO;
import com.DuocucEcommerce.ConfiguracionPc.Dto.ConfiguracionPCUpdateDTO;
import com.DuocucEcommerce.ConfiguracionPc.Service.ConfiguracionPCService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/configuraciones")
@RequiredArgsConstructor
public class ConfiguracionPCController {
    private final ConfiguracionPCService service;
    @GetMapping public ResponseEntity<List<ConfiguracionPCResponseDTO>> listar() { return ResponseEntity.ok(service.listar()); }
    @GetMapping("/{id}") public ResponseEntity<ConfiguracionPCResponseDTO> buscarPorId(@PathVariable Integer id) { return ResponseEntity.ok(service.buscarPorId(id)); }
    @GetMapping("/usuario/{usuarioId}") public ResponseEntity<List<ConfiguracionPCResponseDTO>> listarPorUsuario(@PathVariable Integer usuarioId) { return ResponseEntity.ok(service.listarPorUsuario(usuarioId)); }
    @PostMapping public ResponseEntity<ConfiguracionPCResponseDTO> crear(@Valid @RequestBody ConfiguracionPCCreateDTO dto) { return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto)); }
    @PutMapping("/{id}") public ResponseEntity<ConfiguracionPCResponseDTO> actualizar(@PathVariable Integer id, @Valid @RequestBody ConfiguracionPCUpdateDTO dto) { return ResponseEntity.ok(service.actualizar(id, dto)); }
    @PostMapping("/{id}/evaluar") public ResponseEntity<ConfiguracionPCResponseDTO> evaluar(@PathVariable Integer id) { return ResponseEntity.ok(service.evaluar(id)); }
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Integer id) { service.eliminar(id); return ResponseEntity.noContent().build(); }
}
